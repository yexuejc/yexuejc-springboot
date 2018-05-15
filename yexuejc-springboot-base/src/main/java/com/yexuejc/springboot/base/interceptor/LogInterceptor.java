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
 * @ClassName: LogInterceptor
 * @Description:
 * @author: maxf
 * @date: 2018/5/15 16:29
 */
public class LogInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 如登录用户访问，则记录其用户名（手机号）
        try {
            String username = "";
            if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null
                    && SecurityContextHolder.getContext().getAuthentication().getName() != null) {
                username = SecurityContextHolder.getContext().getAuthentication().getName();
            }
            String ip = "";
            ip = NetUtil.getIp(request);
            // 写日志
            String uri = request.getRequestURI();
            String userAgent = LogUtil.format(request.getHeader(HttpHeaders.USER_AGENT));
            String xuserAgent = request.getHeader(RespsConsts.HEADER_X_USER_AGENT);

            LogUtil.accessLogger.info("{};{};{};{};{}", uri, userAgent, xuserAgent, ip, username);
        } catch (Exception e) {
            LogUtil.exceptionLogger.error(e.getMessage(), e);
        }
        return true;
    }

}
