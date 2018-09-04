package com.yexuejc.springboot.base.filter;

import com.yexuejc.base.constant.RespsConsts;
import com.yexuejc.base.http.Resps;
import com.yexuejc.base.util.JsonUtil;
import com.yexuejc.springboot.base.util.LogUtil;
import com.yexuejc.springboot.base.util.NetUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 过滤器配置
 *
 * @version 1.0.5
 * @ClassName: ValidationFilter
 * @Description:
 * @author: maxf
 * @date: 2018/5/14 17:49
 */
public class ValidationFilter implements Filter {
    ValidationFilterProperties properties;

    public ValidationFilter(ValidationFilterProperties properties) {
        this.properties = properties;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 请求安全
     * 解决方案：
     * header头添加X-User-Agent：授权
     * 入参取时间戳，先MD5，然后进行RSA非对称加密
     * 按顺序解密：
     * 1.防泄漏：RSA解密
     * 2.防篡改：md5参数校验
     * 3.防重复请求：时间戳（一分钟时间差）
     * 4.防XSS攻击：header头添加X-User-Agent授权
     * 5.登录角色token授权
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        if (!(servletRequest instanceof HttpServletRequest) || !(servletResponse instanceof HttpServletResponse)) {
            throw new ServletException("OncePerRequestFilter just supports HTTP requests");
        }
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (RequestMethod.OPTIONS.name().equals(request.getMethod())) {
            //不拦截 OPTIONS 请求
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String sp = request.getServletPath();
        if (properties.getType() == 0) {
            AtomicBoolean b = new AtomicBoolean(false);
            properties.getIgnored().forEach(it -> {
                if (it.endsWith("/**") && sp.indexOf(it.substring(0, it.length() - 3)) > -1) {
                    b.set(true);
                    return;
                }
            });
            if (b.get() || properties.getIgnored().contains(sp)) {
                //不拦截
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        } else {
            AtomicBoolean b = new AtomicBoolean(true);
            properties.getIntercepts().forEach(it -> {
                if (it.endsWith("/**") && sp.indexOf(it.substring(0, it.length() - 3)) > -1) {
                    b.set(false);
                    return;
                }
            });
            if (b.get() || !properties.getIntercepts().contains(sp)) {
                //不拦截
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        /**
         * stop1：header头 校验
         */
        String xuserAgent = request.getHeader(RespsConsts.HEADER_X_USER_AGENT);
        if (xuserAgent == null || xuserAgent.length() == 0) {
            // 写日志
            String uri = request.getRequestURI();
            String userAgent = LogUtil.format(request.getHeader(HttpHeaders.USER_AGENT));
            String ip = NetUtil.getIp(request);
            LogUtil.accessLogger.warn("{};{};{};{};", uri, userAgent, xuserAgent, ip);
            // 返回Response->400-E:请求错误
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().write(JsonUtil.obj2Json(Resps.error(RespsConsts.CODE_ERROR, RespsConsts
                    .MSG_ERROT_HTTP)));
            response.getWriter().close();
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }


}
