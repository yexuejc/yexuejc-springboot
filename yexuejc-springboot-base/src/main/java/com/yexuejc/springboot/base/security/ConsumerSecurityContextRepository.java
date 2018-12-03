package com.yexuejc.springboot.base.security;

import com.yexuejc.base.constant.RespsConsts;
import com.yexuejc.base.util.JwtUtil;
import com.yexuejc.springboot.base.constant.BizConsts;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Security 上下文配置
 * <p>
 * 校验token=>返回token携带用户（登录用户）信息
 * </p>
 *
 * @ClassName: ConsumerSecurityContextRepository
 * @Description:Security 上下文配置
 * @author: maxf
 * @date: 2017年11月22日 下午4:39:20
 */
public class ConsumerSecurityContextRepository implements SecurityContextRepository {
    protected final Log logger = LogFactory.getLog(this.getClass());
    protected static final String TOKEN = "token";
    protected static final String ROLES = "roles";

    protected final RedisTemplate<Object, Object> redisTemplate0;

    public ConsumerSecurityContextRepository(RedisTemplate<Object, Object> redisTemplate0) {
        this.redisTemplate0 = redisTemplate0;
    }

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        SecurityContext context = readSecurityContext(requestResponseHolder.getRequest());
        if (context == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("No SecurityContext was available, A new one will be created.");
            }
            context = SecurityContextHolder.createEmptyContext();
        }
        return context;
    }

    /**
     * Stores the security context on completion of a request.
     * <p>Title: saveContext</p>
     * <p>Description:Stores the security context on completion of a request. </p>
     *
     * @param context
     * @param request
     * @param response
     * @see SecurityContextRepository#saveContext(SecurityContext, HttpServletRequest, HttpServletResponse)
     */
    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        return readSecurityContext(request) != null;
    }

    /**
     * <pre>
     * 过滤登录认证
     * 默认：header=>Authorization:token
     * token 为 jwt串
     * 此处使用登录返回的token解析到具体的登录用户,返回登录用户信息；如果没有，者无权限请求该接口
     * </pre>
     *
     * @param request
     */
    protected SecurityContext readSecurityContext(HttpServletRequest request) {
        final boolean debug = logger.isDebugEnabled();

        String token = request.getHeader(RespsConsts.HEADER_AUTHORIZATION);
        if (token == null || token.length() == 0) {
            return null;
        }
        LoginToken consumerToken = JwtUtil.instace().parse(token, LoginToken.class);
        if (consumerToken == null || consumerToken.getUsername() == null || consumerToken.getUsername().length() == 0) {
            return null;
        }
        // 根据token中携带的username查询用户信息
        Map<Object, Object> entry = redisTemplate0.opsForHash()
                .entries(BizConsts.CONSUMER_LOGIN_REDIS + "." + consumerToken.getUsername());
        if (entry == null) {
            return null;
        }
        if (!token.equals(entry.get(TOKEN))) {
            return null;
        }
        // 处理用户权限
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : (List<String>) entry.get(ROLES)) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        ConsumerUser consumerUser = new ConsumerUser((String) entry.get("username"), "",
                true, true, true, true, authorities,
                (String) entry.get("id"), (String) entry.get("logType"), (Long) entry.get("logTime"));

        SecurityContext context = new SecurityContextImpl();

        consumerUser.eraseCredentials();
        context.setAuthentication(
                new UsernamePasswordAuthenticationToken(consumerUser, null, consumerUser.getAuthorities()));

        if (debug) {
            logger.debug("Obtained a valid SecurityContext : '" + context + "'");
        }
        return context;
    }

}
