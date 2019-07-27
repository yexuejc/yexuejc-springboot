package com.example.demo.security;

import com.yexuejc.base.constant.RespsConsts;
import com.yexuejc.base.http.Resps;
import com.yexuejc.base.util.JsonUtil;
import com.yexuejc.base.util.JwtUtil;
import com.yexuejc.base.util.RegexUtil;
import com.yexuejc.base.util.StrUtil;
import com.yexuejc.springboot.base.autoconfigure.MutiRedisAutoConfiguration;
import com.yexuejc.springboot.base.constant.BizConsts;
import com.yexuejc.springboot.base.exception.ThirdPartyAuthorizationException;
import com.yexuejc.springboot.base.exception.UserNotAuthoriayException;
import com.yexuejc.springboot.base.security.ConsumerAuthenticationProcessingFilter;
import com.yexuejc.springboot.base.security.ConsumerUser;
import com.yexuejc.springboot.base.security.LoginToken;
import com.yexuejc.springboot.base.security.SecurityConfig;
import com.yexuejc.springboot.base.security.inte.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.SecurityContextRepository;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author maxf
 * @version 1.0
 * @ClassName SecurityConfig
 * @Description
 * @date 2018/11/8 17:30
 */
@EnableWebSecurity(debug = false)
public class MySecurityConfig extends SecurityConfig {

    @Autowired
    @Qualifier("userserviceimpl")
    UserService userService;

    @Override
    protected UserService getUserService() {
        return userService;
    }

    @Autowired
    @Qualifier(MutiRedisAutoConfiguration.BEAN_REDIS_TEMPLATE0)
    private RedisTemplate<Object, Object> redisTemplate0;

    @Override
    protected RedisTemplate<Object, Object> getRedisDB() {
        return redisTemplate0;
    }

    //自定义==================================================================

    @Override
    protected AuthenticationProvider createConsumerAuthenticationProvider(UserDetailsService userDetailsService) {
        return super.createConsumerAuthenticationProvider(userDetailsService);
    }

    @Override
    protected ConsumerAuthenticationProcessingFilter createConsumerAuthenticationProcessingFilter(AuthenticationManager authenticationManager) {
        return super.createConsumerAuthenticationProcessingFilter(authenticationManager);
    }

    @Override
    protected SecurityContextRepository createConsumerSecurityContextRepository() {
        return super.createConsumerSecurityContextRepository();
    }

    @Override
    protected UserDetailsService createUserDetailsManager() {
        return super.createUserDetailsManager();
    }
    //自定义==================================================================


    /**
     * 保存登录信息至redis
     *
     * @param redisTemplate0 redis 链接
     * @param user           登录用户
     * @param roles          角色信息
     * @param token          登录token
     * @param isPast         是否设置过期
     */
    private void saveLoginUser(RedisTemplate<Object, Object> redisTemplate0, ConsumerUser user, List<String> roles, String token,
                               boolean isPast) {
        Map<String, Object> m = new HashMap<>(4);
        m.put("username", user.getUsername());
        m.put("token", token);
        m.put("roles", roles);
        m.put("id", user.getId());
        m.put("logType", user.getLogType());
        m.put("logTime", user.getLogTime());
        redisTemplate0.opsForHash().putAll(BizConsts.CONSUMER_LOGIN_REDIS + "." + user.getUsername(), m);
        if (isPast) {
            //对于没有绑定手机号的token，10分钟后过期
            redisTemplate0.expire(BizConsts.CONSUMER_LOGIN_REDIS + "." + user.getUsername(), 10, TimeUnit.MINUTES);
        }
    }

    /**
     * <pre>
     * 处理登录
     * 成功: filter.setAuthenticationSuccessHandler()
     * 失败: filter.setAuthenticationFailureHandler()
     * </pre>
     *
     * @param filter
     */
    @Override
    protected void loginHodler(ConsumerAuthenticationProcessingFilter filter) {
        filter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            String token = JwtUtil.instace().compact(new LoginToken(authentication.getName()));
            ConsumerUser user = (ConsumerUser) authentication.getPrincipal();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            List<String> roles = new ArrayList<>();
            if (authorities != null && authorities.size() > 0) {
                for (GrantedAuthority g : authorities) {
                    roles.add(g.getAuthority());
                }
            }
            Map<String, Object> map = new HashMap<>(2);
            map.put("token", token);
            map.put("bindMobile", false);
            if (StrUtil.isEmpty(user.getUsername()) || !RegexUtil.regex(user.getUsername(), RegexUtil.REGEX_MOBILE)) {
                map.put("bindMobile", true);
            }
            saveLoginUser(redisTemplate0, user, roles, token, (Boolean) map.get("bindMobile"));
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JsonUtil.obj2Json(Resps.success().setSucc(map)));
            response.getWriter().close();
        });
        filter.setAuthenticationFailureHandler((request, response, exception) -> {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            Resps resps = new Resps();
            if (exception instanceof DisabledException) {
                resps.setErr(RespsConsts.CODE_FAIL, new String[]{BizConsts.BASE_IS_LOCK_MSG});
            } else if (exception instanceof AccountExpiredException) {
                resps.setErr(RespsConsts.CODE_FAIL, new String[]{BizConsts.BASE_IS_EXPIRE_MSG});
            } else if (exception instanceof CredentialsExpiredException) {
                resps.setErr(BizConsts.BASE_LOGIN_IS_EXPIRE_CODE, new String[]{BizConsts.BASE_LOGIN_IS_EXPIRE_MSG});
            } else if (exception instanceof LockedException) {
                resps.setErr(RespsConsts.CODE_FAIL, new String[]{BizConsts.BASE_IS_LOCKED_MSG});
            } else if (exception instanceof AuthenticationCredentialsNotFoundException) {
                resps.setErr(RespsConsts.CODE_FAIL, new String[]{BizConsts.BASE_CREDENTIALS_NOT_FOUND_MSG});
            } else if (exception instanceof ThirdPartyAuthorizationException) {
                resps.setErr(RespsConsts.CODE_FAIL, new String[]{exception.getMessage()});
            } else if (exception instanceof BadCredentialsException) {
                resps.setErr(RespsConsts.CODE_FAIL, new String[]{BizConsts.BASE_PWD_IS_ERR_MSG});
            } else if (exception instanceof UsernameNotFoundException) {
                resps.setErr(RespsConsts.CODE_FAIL, new String[]{BizConsts.BASE_ACCOUNT_NOT_FOUND_MSG});
            } else if (exception instanceof UserNotAuthoriayException) {
                resps.setErr(RespsConsts.CODE_FAIL, new String[]{exception.getMessage()});
            } else {
                resps.setErr(RespsConsts.CODE_FAIL, new String[]{BizConsts.BASE_SYS_ERR_MSG});
            }
            response.getWriter().write(JsonUtil.obj2Json(resps));
            response.getWriter().close();
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.headers().frameOptions().disable();
        /**
         * 权限控制
         * ->无权限
         */
        http.authorizeRequests().antMatchers(
                "/", "/index"
        ).permitAll();

        /**
         * 权限控制
         * ->登录可访问
         */
        http.authorizeRequests().antMatchers(
                "/consumer/**", "/sms/bind/**").authenticated();

        // 登出处理。
        http.logout().logoutSuccessHandler((request, response, authentication) -> {
            redisTemplate0.delete(BizConsts.CONSUMER_LOGIN_REDIS + "." + authentication.getName());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JsonUtil.obj2Json(Resps.success()));
            response.getWriter().close();
        });

        // 未登录，却访问需要登录的接口时的处理
        http.exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(401);
            response.getWriter().write(
                    JsonUtil.obj2Json(
                            Resps.error(RespsConsts.CODE_FAIL, new String[]{BizConsts.BASE_NOT_LOGIN_MSG})
                    )
            );
            response.getWriter().close();
        });
        // 已登录，但当前用户没有访问的某个接口的权限时的处理
        http.exceptionHandling().accessDeniedHandler((request, response, accessDeniedException) -> {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(401);
            response.getWriter().write(
                    JsonUtil.obj2Json(
                            Resps.error(RespsConsts.CODE_FAIL, new String[]{BizConsts.BASE_NOT_ROLE_MSG})
                    )
            );
            response.getWriter().close();
        });
    }

}
