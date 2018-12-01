package com.yexuejc.springboot.base.security.inte;

import com.yexuejc.base.pojo.ApiVO;
import com.yexuejc.springboot.base.constant.BizConsts;
import com.yexuejc.springboot.base.exception.UserNotAuthoriayException;
import com.yexuejc.springboot.base.security.ConsumerToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 用户登录处理接口
 *
 * @author maxf
 * @version 1.0
 * @ClassName UserService
 * @Description
 * @date 2018/11/8 17:17
 */
public interface UserService {
    /**
     * 根据用户名到数据库查询用户
     * <p>
     * <p>
     * <p>
     * 获取登录用户信息返回结果类型必须是
     * {@link com.yexuejc.springboot.base.security.inte.User}实现类<br/>
     * 或者{@link com.yexuejc.springboot.base.security.ConsumerUser}继承类
     * </p>
     *
     * @param username 登录账号
     * @return
     */
    Object getConsumerByUserName(String username) throws UserNotAuthoriayException, UsernameNotFoundException;

    /**
     * 校验短信验证码=>短信登录
     *
     * @param redisBiz {@link BizConsts.CONSUMER_LOGIN_SMS} 业务id reids使用
     * @param username 登录账号
     * @param smscode  短信验证码
     * @return
     */
    ApiVO checkSmsCode2Redis(String redisBiz, String username, String smscode);

    /**
     * 校验第三方登录openid
     * <p>
     * 获取登录用户信息返回结果类型必须是
     * {@link com.yexuejc.springboot.base.security.inte.User}实现类<br/>
     * 或者{@link org.springframework.security.core.userdetails.UserDetails}实现类<br/>
     * 或者{@link com.yexuejc.springboot.base.security.ConsumerUser}继承类
     * </p>
     *
     * @param consumerToken 登录信息
     * @return 自己封装登录用户信息
     */
    Object checkOpenId(ConsumerToken consumerToken);

    /**
     * 没有账号时根据登录信息创建账号
     * <p>
     * 获取登录用户信息返回结果类型必须是
     * {@link com.yexuejc.springboot.base.security.inte.User}实现类<br/>
     * 或者{@link org.springframework.security.core.userdetails.UserDetails}实现类<br/>
     * 或者{@link com.yexuejc.springboot.base.security.ConsumerUser}继承类
     * </p>
     *
     * @param consumerToken 登录信息
     * @return 自己封装登录用户信息
     */
    Object addConsumer(ConsumerToken consumerToken);
}
