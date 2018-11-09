package com.yexuejc.springboot.base.security.inte;

import com.yexuejc.base.pojo.ApiVO;
import com.yexuejc.springboot.base.constant.BizConsts;
import com.yexuejc.springboot.base.security.ConsumerToken;

/**
 * @author maxf
 * @version 1.0
 * @ClassName UserService
 * @Description
 * @date 2018/11/8 17:17
 */
public interface UserService {
    /**
     * 根据用户名到数据库查询用户
     *
     * @param username 登录账号
     * @return
     */
    User getConsumerByUserName(String username);

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
     *
     * @param consumerToken 登录信息
     * @return apiVO.setObject1(User.class) 自己封装登录用户信息
     */
    ApiVO checkOpenId(ConsumerToken consumerToken);

    /**
     * 没有账号时根据登录信息创建账号
     *
     * @param consumerToken 登录信息
     * @return
     */
    ApiVO addConsumer(ConsumerToken consumerToken);
}
