package com.yexuejc.springboot.base.interceptor;

import com.yexuejc.base.constant.RespsConsts;
import com.yexuejc.springboot.base.util.LogUtil;
import com.yexuejc.springboot.base.util.NetUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 访问日志拦截器，用于记录用户访问日志
 * 
 * @author PHY
 */
public class LogInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 如登录用户访问，则记录其用户名（手机号）
        String username = null;
        if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().getName() != null) {
            username = SecurityContextHolder.getContext().getAuthentication().getName();
        }
        // 写日志
        String uri = request.getRequestURI();
        String userAgent = LogUtil.format(request.getHeader(HttpHeaders.USER_AGENT));
        String xuserAgent = request.getHeader(RespsConsts.HEADER_X_USER_AGENT);
        String ip = NetUtil.getIp(request);

        LogUtil.accessLogger.info("{};{};{};{};{}", uri, userAgent, xuserAgent, ip, username);
        return true;
    }

}
