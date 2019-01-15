package com.yexuejc.springboot.base.security;

import com.yexuejc.base.pojo.ApiVO;
import com.yexuejc.base.util.StrUtil;
import com.yexuejc.springboot.base.constant.BizConsts;
import com.yexuejc.springboot.base.constant.LogTypeConsts;
import com.yexuejc.springboot.base.exception.ClassConvertException;
import com.yexuejc.springboot.base.exception.ThirdPartyAuthorizationException;
import com.yexuejc.springboot.base.security.inte.User;
import com.yexuejc.springboot.base.security.inte.UserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h2>主要做验证用户使用</h2>
 * <pre>
 *     密码登录：校验账号密码
 *     短信登录：校验短信验证码
 *     第三方登录：校验openid=>用户中心（数据库）不存在，进行新增数据操作
 * </pre>
 *
 * @author maxf
 * @version 1.0
 * @ClassName ConsumerAuthenticationProvider
 * @Description
 * @date 2018/11/9 11:23
 */
public class ConsumerAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    // ~ Static fields/initializers
    // =====================================================================================

    /**
     * The plaintext password used to perform
     * PasswordEncoder#matches(CharSequence, String)}  on when the user is
     * not found to avoid SEC-2056.
     */
    protected static final String USER_NOT_FOUND_PASSWORD = "userNotFoundPassword";

    // ~ Instance fields
    // ================================================================================================

    protected PasswordEncoder passwordEncoder;

    /**
     * The password used to perform
     * {@link PasswordEncoder#matches(CharSequence, String)} on when the user is
     * not found to avoid SEC-2056. This is necessary, because some
     * {@link PasswordEncoder} implementations will short circuit if the password is not
     * in a valid format.
     */
    protected volatile String userNotFoundEncodedPassword;

    protected UserDetailsService userDetailsService;
    protected final UserService accountView;

    public ConsumerAuthenticationProvider(UserDetailsService userDetailsService, UserService accountView) {
//        super();
        super.setHideUserNotFoundExceptions(false);
//        // 这个地方一定要对userDetailsService赋值，不然userDetailsService是null (这个坑有点深)
        setUserDetailsService(userDetailsService);
        setPasswordEncoder(createDelegatingPasswordEncoder());
        this.accountView = accountView;
    }

    /**
     * 定义加密方式
     * 默认MD5加密
     *
     * @return
     */
    protected PasswordEncoder createDelegatingPasswordEncoder() {
        String encodingId = "MD5";
        Map<String, PasswordEncoder> encoders = new HashMap<>(9);
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("ldap", new LdapShaPasswordEncoder());
        encoders.put("MD4", new Md4PasswordEncoder());
        encoders.put("MD5", new MessageDigestPasswordEncoder("MD5"));
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());
        encoders.put("SHA-1", new MessageDigestPasswordEncoder("SHA-1"));
        encoders.put("SHA-256", new MessageDigestPasswordEncoder("SHA-256"));
        encoders.put("sha256", new StandardPasswordEncoder());
        return new DelegatingPasswordEncoder(encodingId, encoders);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        String requestPwd = null;
        try {
            requestPwd = authentication.getCredentials().toString();
        } catch (Exception e) {
            throw new BadCredentialsException("密码错误");
        }
        if (authentication instanceof ConsumerToken) {
            ConsumerToken consumerToken = (ConsumerToken) authentication;

            displyAuthenticationChecks(consumerToken, requestPwd, userDetails.getPassword());
        }
    }

    /**
     * 登录密码校验
     *
     * @param consumerToken 登录信息
     * @param requestPwd    登录密码
     * @param dbPwd         源用户密码（数据库中的密码）
     */
    protected void displyAuthenticationChecks(ConsumerToken consumerToken, String requestPwd, String dbPwd) {
        ApiVO apiVO;
        switch (consumerToken.getLogtype()) {
            case LogTypeConsts.SMS:
                //新注册账号已经在注册的时候校验的短信验证码，所有这里不校验
                if (!consumerToken.isReg) {
                    apiVO = accountView.checkSmsCode2Redis(BizConsts.CONSUMER_LOGIN_SMS, consumerToken.getUsername(), consumerToken.getSmscode());
                    if (apiVO.isFail()) {
                        throw new ThirdPartyAuthorizationException(apiVO.getMsg());
                    }
                }
                break;
            case LogTypeConsts.QQ:
                break;
            case LogTypeConsts.WECHAT:

                break;
            case LogTypeConsts.WEIBO:
                break;
            default:
                //账号登录
                if (!passwordEncoder.matches(requestPwd, "{MD5}" + dbPwd)) {
                    logger.debug("Authentication failed: password does not match stored value");

                    throw new ThirdPartyAuthorizationException("密码错误");
                }
                break;
        }
    }

    @Override
    protected void doAfterPropertiesSet() throws Exception {
        Assert.notNull(this.userDetailsService, "A UserDetailsService must be set");
    }

    @Override
    protected final UserDetails retrieveUser(String username,
                                             UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        UserDetails loadedUser = null;

        String logtype = "";
        ConsumerToken consumerToken = null;
        if (authentication instanceof ConsumerToken) {
            consumerToken = (ConsumerToken) authentication;
            logtype = StrUtil.setStr(consumerToken.getLogtype(), "");
        }
        try {
            //通过username查询数据库，当第三方登录时所传参数为openid(没有传username),此次会抛出找DB不到账号信息的异常，交给catch处理
            prepareTimingAttackProtection();
            loadedUser = this.getUserDetailsService().loadUserByUsername(username);
        } catch (UsernameNotFoundException notFound) {
            //账号登录
            if ((StrUtil.isEmpty(logtype) || LogTypeConsts.ACCOUNT.equals(logtype))) {
                mitigateAgainstTimingAttack(authentication);
                throw notFound;
            } else {
                try {
                    return third(consumerToken, loadedUser, logtype);
                } catch (Exception e) {
                    e.printStackTrace();
                    if (e instanceof ThirdPartyAuthorizationException) {
                        throw e;
                    }
                    throw new ThirdPartyAuthorizationException(e.getMessage());
                }
            }
        } catch (Exception repositoryProblem) {
            throw new InternalAuthenticationServiceException(
                    repositoryProblem.getMessage(), repositoryProblem);
        }
        if (loadedUser == null) {
            throw new InternalAuthenticationServiceException(
                    "UserDetailsService returned null, which is an interface contract violation");
        }
        return loadedUser;
    }

    /**
     * 第三方登录处理=>登录用户为空，此方法处理返回登录用户
     *
     * @param consumerToken 登录信息
     * @param loadedUser    登录用户（为空时进入此方法）
     * @param logtype       登录方式
     * @return 登录用户
     */
    protected UserDetails third(ConsumerToken consumerToken, UserDetails loadedUser, String logtype) {
        //其他方式登录:查询账号 没有->创建账号
        //第三方登录
        if (consumerToken != null && StrUtil.isNotEmpty(consumerToken.getOpenid())) {
            Object obj = accountView.checkOpenId(consumerToken);
            if (obj != null) {

                //已有账号
                if (obj instanceof User) {
                    User consumer = (User) obj;
                    // 处理用户权限
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    for (String role : consumer.getRoles()) {
                        authorities.add(new SimpleGrantedAuthority(role));
                    }
                    loadedUser = new ConsumerUser(
                            StrUtil.isEmpty(consumer.getMobile()) ? consumerToken.getOpenid() : consumer.getMobile(),
                            consumer.getPwd(), consumer.getEnable(), consumer.getNonExpire(),
                            true, consumer.getNonLock(), authorities, consumer.getConsumerId(),
                            logtype, System.currentTimeMillis());
                    return loadedUser;
                } else if (obj instanceof UserDetails) {
                    loadedUser = (UserDetails) obj;
                    return loadedUser;
                } else {
                    throw new ClassConvertException("获取登录用户信息返回结果类型必须是com.yexuejc.springboot.base.security.inte.User实现类" +
                            "或者org.springframework.security.core.userdetails.UserDetails实现类" +
                            "或者com.yexuejc.springboot.base.security.ConsumerUser继承类");
                }
            }
        }
        //第三方登录+短信登录
        if (consumerToken != null) {
            //没有->创建账号
            consumerToken.isReg = true;
            Object obj = accountView.addConsumer(consumerToken);
            if (obj != null) {
                if (obj instanceof User) {
                    User consumer = (User) obj;
                    loadedUser = display(consumerToken, consumer);
                    return loadedUser;
                } else if (obj instanceof UserDetails) {
                    loadedUser = (UserDetails) obj;
                    return loadedUser;
                } else {
                    throw new ClassConvertException("获取登录用户信息返回结果类型必须是com.yexuejc.springboot.base.security.inte.User实现类" +
                            "或者org.springframework.security.core.userdetails.UserDetails实现类" +
                            "或者com.yexuejc.springboot.base.security.ConsumerUser继承类");
                }
            } else {
                throw new ThirdPartyAuthorizationException("第三方登录失败");
            }
        } else {
            throw new ThirdPartyAuthorizationException();
        }
    }

    protected void prepareTimingAttackProtection() {
        if (this.userNotFoundEncodedPassword == null) {
            this.userNotFoundEncodedPassword = this.passwordEncoder.encode(USER_NOT_FOUND_PASSWORD);
        }
    }

    protected void mitigateAgainstTimingAttack(UsernamePasswordAuthenticationToken authentication) {
        if (authentication.getCredentials() != null) {
            String presentedPassword = authentication.getCredentials().toString();
            this.passwordEncoder.matches(presentedPassword, "{MD5}" + this.userNotFoundEncodedPassword);
        }
    }

    /**
     * 处理用户为登录用户
     *
     * @param consumerToken 登录用户信息
     * @param consumer      实际用户信息
     * @return response User
     */
    protected UserDetails display(ConsumerToken consumerToken, User consumer) {
        // 处理用户权限
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : consumer.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        UserDetails userDetails = new ConsumerUser(
                StrUtil.isEmpty(consumer.getMobile()) ? consumerToken.getOpenid() : consumer.getMobile(),
                consumer.getPwd(), consumer.getEnable(), consumer.getNonExpire(),
                true, consumer.getNonLock(), authorities, consumer.getConsumerId(),
                consumerToken.getLogtype(), System.currentTimeMillis());
        return userDetails;
    }

    /**
     * Sets the PasswordEncoder instance to be used to encode and validate passwords. If
     * not set, the password will be compared using {@link PasswordEncoderFactories#createDelegatingPasswordEncoder()}
     *
     * @param passwordEncoder must be an instance of one of the {@code PasswordEncoder}
     *                        types.
     */
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
        this.passwordEncoder = passwordEncoder;
        this.userNotFoundEncodedPassword = null;
    }

    protected PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    protected UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }
}