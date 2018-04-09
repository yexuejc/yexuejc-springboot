package com.yexuejc.springboot.base.filter;

import com.yexuejc.base.constant.RespsConsts;
import com.yexuejc.base.http.Resps;
import com.yexuejc.base.util.JsonUtil;
import com.yexuejc.springboot.base.util.LogUtil;
import com.yexuejc.springboot.base.util.NetUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ValidationFilter implements Filter {

    ValidationFilterProperties properties;

    public ValidationFilter(ValidationFilterProperties properties) {
        this.properties = properties;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        if (!(servletRequest instanceof HttpServletRequest) || !(servletResponse instanceof HttpServletResponse)) {
            throw new ServletException("OncePerRequestFilter just supports HTTP requests");
        }
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String sp = request.getServletPath();
        if (properties.getType() == 0) {
            if (properties.getIgnored().contains(sp)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        } else {
            if (!properties.getIntercepts().contains(sp)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
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
