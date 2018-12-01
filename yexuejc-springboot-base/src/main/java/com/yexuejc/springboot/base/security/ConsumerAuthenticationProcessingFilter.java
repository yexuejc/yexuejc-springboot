package com.yexuejc.springboot.base.security;

import com.yexuejc.springboot.base.constant.LogTypeConsts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

public class ConsumerAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
    // ~ Static fields/initializers
    // =====================================================================================

    protected static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
    protected static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
    /**
     * 登录方式
     */
    protected static final String SPRING_SECURITY_FORM_LOGTYPE_KEY = "logtype";
    protected static final String SPRING_SECURITY_FORM_OPENID_KEY = "openid";
    /********************************** 第三方登录时附带信息*************************************/
    /**
     * 头像
     */
    protected static final String SPRING_SECURITY_FORM_HEAD_KEY = "head";
    /**
     * 昵称
     */
    protected static final String SPRING_SECURITY_FORM_NICKNAME_KEY = "nickname";
    /**
     * 性别
     */
    protected static final String SPRING_SECURITY_FORM_SEX_KEY = "sex";
    /********************************** 第三方登录时附带信息*************************************/

   protected String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
   protected String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;
   protected String logtypeParameter = SPRING_SECURITY_FORM_LOGTYPE_KEY;
   protected String openidParameter = SPRING_SECURITY_FORM_OPENID_KEY;
   protected String headParameter = SPRING_SECURITY_FORM_HEAD_KEY;
   protected String nicknameParameter = SPRING_SECURITY_FORM_NICKNAME_KEY;
   protected String sexParameter = SPRING_SECURITY_FORM_SEX_KEY;
   protected boolean postOnly = true;
   protected boolean reverse = true;

    // ~ Constructors
    // ===================================================================================================

    protected ConsumerAuthenticationProcessingFilter(AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher("/login", "POST"));
        setAuthenticationManager(authenticationManager);
    }

    protected ConsumerAuthenticationProcessingFilter() {
        super(new AntPathRequestMatcher("/login", "POST"));
    }
    // ~ Methods
    // ========================================================================================================

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        return displyAuthentication(request, response);
    }

    /**
     * 可继承自定义处理登录请求
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    protected Authentication displyAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        UsernamePasswordAuthenticationToken authRequest = getParams(request);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        //ProviderManager 这东西会先把DaoAuthenticationProvider加入列表，导致setHideUserNotFoundExceptions(false);被覆盖
        if (this.getAuthenticationManager() instanceof ProviderManager && reverse) {
            reverse = false;
            ProviderManager providerManager = (ProviderManager) this.getAuthenticationManager();
            providerManager.getProviders().forEach(it -> {
                ((AbstractUserDetailsAuthenticationProvider) it).setHideUserNotFoundExceptions(false);
            });
            Collections.reverse(providerManager.getProviders());
        }
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * 根据登录方式获取请求参数
     *
     * @param request 登录请求
     */
    protected UsernamePasswordAuthenticationToken getParams(HttpServletRequest request) {
        String logtype = obtainLogtype(request);
        System.out.println("登录方式：" + logtype);
        String username = "";
        String password = "";
        if (logtype == null) {
            logtype = "";
        }
        String openid = "";
        String smscode = "";
        /**第三方登录：微信 用户头像*/
        String head = "";
        String nickname = "";
        String sex = "";
        //根据不同登录方式做不同处理
        switch (logtype) {
            case LogTypeConsts.SMS:
                //短信登录
                username = obtainUsername(request);
                smscode = obtainPassword(request);
                break;
            case LogTypeConsts.QQ:
                //QQ登录
                openid = obtainOpenid(request);
                head = obtainHead(request);
                nickname = obtainNickname(request);
                sex = obtainSex(request);
                break;
            case LogTypeConsts.WECHAT:
                //微信登录
                openid = obtainOpenid(request);
                head = obtainHead(request);
                nickname = obtainNickname(request);
                sex = obtainSex(request);
                break;
            case LogTypeConsts.WEIBO:
                //微博登录
                openid = obtainOpenid(request);
                head = obtainHead(request);
                nickname = obtainNickname(request);
                sex = obtainSex(request);
                break;
            default:
                //默认账号+密码登录
                username = obtainUsername(request).trim();
                password = obtainPassword(request);
                break;
        }
        return new ConsumerToken(
                logtype, smscode, openid, username, password, head, nickname, sex);
    }

    /**
     * Enables subclasses to override the composition of the password, such as by
     * including additional values and a separator.
     * <p>
     * This might be used for example if a postcode/zipcode was required in addition to
     * the password. A delimiter such as a pipe (|) should be used to separate the
     * password and extended value(s). The <code>AuthenticationDao</code> will need to
     * generate the expected password in a corresponding manner.
     * </p>
     *
     * @param request so that request attributes can be retrieved
     * @return the password that will be presented in the <code>Authentication</code>
     * request token to the <code>AuthenticationManager</code>
     */
    protected String obtainPassword(HttpServletRequest request) {
        return request.getParameter(passwordParameter);
    }

    /**
     * Enables subclasses to override the composition of the username, such as by
     * including additional values and a separator.
     *
     * @param request so that request attributes can be retrieved
     * @return the username that will be presented in the <code>Authentication</code>
     * request token to the <code>AuthenticationManager</code>
     */
    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(usernameParameter);
    }

    protected String obtainLogtype(HttpServletRequest request) {
        return request.getParameter(logtypeParameter);
    }

    protected String obtainOpenid(HttpServletRequest request) {
        return request.getParameter(openidParameter);
    }

    protected String obtainHead(HttpServletRequest request) {
        return request.getParameter(headParameter);
    }

    protected String obtainNickname(HttpServletRequest request) {
        return request.getParameter(nicknameParameter);
    }

    protected String obtainSex(HttpServletRequest request) {
        return request.getParameter(sexParameter);
    }

    /**
     * Provided so that subclasses may configure what is put into the authentication
     * request's details property.
     *
     * @param request     that an authentication request is being created for
     * @param authRequest the authentication request object that should have its details
     *                    set
     */
    protected void setDetails(HttpServletRequest request,
                              UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    /**
     * Sets the parameter name which will be used to obtain the username from the login
     * request.
     *
     * @param usernameParameter the parameter name. Defaults to "username".
     */
    public void setUsernameParameter(String usernameParameter) {
        Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
        this.usernameParameter = usernameParameter;
    }

    /**
     * Sets the parameter name which will be used to obtain the password from the login
     * request..
     *
     * @param passwordParameter the parameter name. Defaults to "password".
     */
    public void setPasswordParameter(String passwordParameter) {
        Assert.hasText(passwordParameter, "Password parameter must not be empty or null");
        this.passwordParameter = passwordParameter;
    }

    /**
     * Defines whether only HTTP POST requests will be allowed by this filter. If set to
     * true, and an authentication request is received which is not a POST request, an
     * exception will be raised immediately and authentication will not be attempted. The
     * <tt>unsuccessfulAuthentication()</tt> method will be called as if handling a failed
     * authentication.
     * <p>
     * Defaults to <tt>true</tt> but may be overridden by subclasses.
     */
    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getUsernameParameter() {
        return usernameParameter;
    }

    public final String getPasswordParameter() {
        return passwordParameter;
    }
}
