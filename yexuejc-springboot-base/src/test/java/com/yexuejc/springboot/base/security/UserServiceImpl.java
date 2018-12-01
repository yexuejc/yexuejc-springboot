package com.yexuejc.springboot.base.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yexuejc.base.constant.RespsConsts;
import com.yexuejc.base.pojo.ApiVO;
import com.yexuejc.base.util.StrUtil;
import com.yexuejc.springboot.base.autoconfigure.MutiRedisAutoConfiguration;
import com.yexuejc.springboot.base.constant.BizConsts;
import com.yexuejc.springboot.base.constant.DictRegTypeConsts;
import com.yexuejc.springboot.base.constant.LogTypeConsts;
import com.yexuejc.springboot.base.exception.ThirdPartyAuthorizationException;
import com.yexuejc.springboot.base.mapper.ConsumerMapper;
import com.yexuejc.springboot.base.security.domain.Consumer;
import com.yexuejc.springboot.base.security.inte.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库操作DB
 *
 * @author maxf
 * @version 1.0
 * @ClassName UserServiceImpl
 * @Description
 * @date 2018/11/8 17:31
 */
@Service("userserviceimpl")
public class UserServiceImpl implements UserService {
    @Autowired
    ConsumerMapper consumerMapper;

    @Autowired
    @Qualifier(MutiRedisAutoConfiguration.BEAN_REDIS_TEMPLATE1)
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 根据用户名到数据库查询用户
     *
     * @param username 登录账号
     * @return
     */
    @Override
    public Object getConsumerByUserName(String username) {
        if (StrUtil.isEmpty(username)) {
            throw new UsernameNotFoundException("username为空，一般是第三方登录来的，直接抛出UsernameNotFoundException就是");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("mobile", username);
        Consumer consumer = consumerMapper.selectOne(queryWrapper);
        if (null == consumer) {
            /**
             *  1.抛出UsernameNotFoundException这个异常如果是第三方登录会走 {@link #checkOpenId(ConsumerToken)}
             *  2.抛出其他Exception可以自己到{@link MySecurityConfig#loginHodler(ConsumerAuthenticationProcessingFilter)}
             *  里面的filter.setAuthenticationFailureHandler()中做特殊处理
             */
            throw new UsernameNotFoundException("没有该账号相关信息");
        }
        //h2不支持json，人为处理角色
        ArrayList roles = new ArrayList<>();
        roles.add("ROLE_CONSUMER");
        consumer.setRoles(roles);
        //1.consumer为User的实现类
//        return consumer;

        //2. 自己创建ConsumerUser，直接返回
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : consumer.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        ConsumerUser consumerUser = new ConsumerUser(consumer.getMobile(), consumer.getPwd(),
                consumer.getEnable(), consumer.getNonExpire(), true, consumer.getNonLock(),
                authorities, consumer.getConsumerId(), null, System.currentTimeMillis());
        return consumerUser;
    }

    /**
     * 到自己的reids里面校验短信验证码
     *
     * @param smsType {@linkplain BizConsts.CONSUMER_LOGIN_SMS} 业务id reids使用
     * @param mobile  登录账号
     * @param code    短信验证码
     * @return
     */
    @Override
    public ApiVO checkSmsCode2Redis(String smsType, String mobile, String code) {
        redisTemplate.afterPropertiesSet();
        String rCode = (String) redisTemplate.opsForHash().get(smsType + "." + mobile, "code");
        Integer validatedNums = (Integer) redisTemplate.opsForHash().get(smsType + "." + mobile, "validatedNums");
        if (validatedNums == null) {
            return new ApiVO(ApiVO.STATUS.F, RespsConsts.CODE_FAIL, "验证码过期，请重新获取");
        } else if (validatedNums > 5) {
            redisTemplate.delete(smsType + "." + mobile);
            return new ApiVO(ApiVO.STATUS.F, RespsConsts.CODE_FAIL, "验证码过期，请重新获取");
        }
        if (code.equals(rCode)) {
            redisTemplate.delete(smsType + "." + mobile);
            return new ApiVO(ApiVO.STATUS.S);
        } else {
            validatedNums++;
            redisTemplate.opsForHash().put(smsType + "." + mobile, "validatedNums", validatedNums);
            return new ApiVO(ApiVO.STATUS.F, RespsConsts.CODE_FAIL, "验证码不正确");
        }
    }

    /**
     * 第三方登录
     * 校验openid 根据自己业务做判断
     *
     * @param consumerToken 登录信息
     * @return
     */
    @Override
    public Object checkOpenId(ConsumerToken consumerToken) {
        ApiVO apiVO = new ApiVO(ApiVO.STATUS.F, "没有找到用户信息");
        switch (consumerToken.getLogtype()) {
            case LogTypeConsts.QQ:
                apiVO = checkOpenid4QQ(consumerToken, true);
                break;
            case LogTypeConsts.WECHAT:
                apiVO = checkOpenid4Wechat(consumerToken, true);
                break;
            case LogTypeConsts.WEIBO:
                apiVO = checkOpenid4Weibo(consumerToken, true);
                break;
            default:
                break;
        }
        if (apiVO.isFail()) {
            /**
             * 未查到:
             * 1.返回null会走（数据库没有这个openid[第三方账号]信息）新增流程 {@link #addConsumer(ConsumerToken)}
             * 2.也可以自己创建一个带有特殊标识的ConsumerUser，然后在 {@link MySecurityConfig#loginHodler(ConsumerAuthenticationProcessingFilter)}
             * 里面的filter.setAuthenticationSuccessHandler()中做特殊处理 ps:假装登录成功 :)
             */
            return null;
        }
        //h2不支持json，人为处理角色
        ArrayList roles = new ArrayList<>();
        roles.add("ROLE_CONSUMER");
        apiVO.getObject1(Consumer.class).setRoles(roles);
        //根据openid到数据库查到consumer返回
        return apiVO.getObject1(Consumer.class);
    }

    /**
     * {@link #checkOpenId(ConsumerToken)} 返回null会走该方法
     * 没有账号时处理自己的业务，此处必须返回 构造出的登录用户，否则会抛出{@link ThirdPartyAuthorizationException 第三方授权异常}
     * <br/>
     *
     * @param consumerToken 登录信息
     * @return
     */
    @Override
    public Object addConsumer(ConsumerToken consumerToken) {
        Consumer consumer = new Consumer();
        consumer.setConsumerId(StrUtil.genUUID());
        consumer.setMobile(StrUtil.isNotEmpty(consumerToken.getUsername()) ? consumerToken.getUsername() : consumerToken.getOpenid());
        consumer.setPwd(StrUtil.toMD5("123456"));
        consumer.setEnable(true);
        consumer.setNonExpire(true);
        consumer.setNonLock(true);
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_CONSUMER");
        consumer.setRoles(roles);
        switch (consumerToken.getLogtype()) {
            case LogTypeConsts.SMS:
                ApiVO apiVO = checkSmsCode2Redis(BizConsts.CONSUMER_LOGIN_SMS, consumerToken.getUsername(),
                        consumerToken.getSmscode());
                if (apiVO.isFail()) {
                    throw new ThirdPartyAuthorizationException("短信验证码错误");
                }
                consumer.setNickname(consumerToken.getUsername());
                consumer.setHead("/head/def.png");
                consumer.setRegType(DictRegTypeConsts.DICT_MOBILE);
                break;
            case LogTypeConsts.QQ:
                consumer.setQqId(consumerToken.getOpenid());
                consumer.setNickname(consumerToken.getNickname());
                setHeader(consumerToken, consumer, false);
                setSex(consumerToken, consumer);
                consumer.setRegType(DictRegTypeConsts.DICT_QQ);
                break;
            case LogTypeConsts.WECHAT:
                consumer.setWechatId(consumerToken.getOpenid());
                consumer.setNickname(consumerToken.getNickname());
                setHeader(consumerToken, consumer, false);
                setSex(consumerToken, consumer);
                consumer.setRegType(DictRegTypeConsts.DICT_WECHAT);
                break;
            case LogTypeConsts.WEIBO:
                consumer.setWeiboId(consumerToken.getOpenid());
                consumer.setNickname(consumerToken.getNickname());
                setHeader(consumerToken, consumer, false);
                setSex(consumerToken, consumer);
                consumer.setRegType(DictRegTypeConsts.DICT_WEIBO);
                break;
            default:
                throw new ThirdPartyAuthorizationException("暂不支持该第三方授权");
        }
        Integer result = consumerMapper.insert(consumer);
        if (result < 1) {
            /**
             * 会抛出{@link ThirdPartyAuthorizationException 第三方授权异常}
             */
            return null;
        }
        return consumer;
    }


    /**
     * 第三方登录 QQ登录
     *
     * @param consumerToken 登录信息
     * @param b
     * @return
     */
    public ApiVO checkOpenid4QQ(ConsumerToken consumerToken, boolean b) {
        Consumer consumer = getConsumerByQQOpenid(consumerToken.getOpenid());
        if (consumer == null) {
            return new ApiVO(ApiVO.STATUS.F, "获取用户信息失败");
        }
        if (b && DictRegTypeConsts.DICT_QQ.equals(consumer.getRegType())) {
            //如果是qq注册的，登录的同时更新用户信息
            updateConsumer(consumer, consumerToken);
        }
        return new ApiVO(ApiVO.STATUS.S).setObject1(consumer);
    }

    /**
     * 第三方登录 微信登录
     *
     * @param consumerToken 登录信息
     * @param b
     * @return
     */
    public ApiVO checkOpenid4Wechat(ConsumerToken consumerToken, boolean b) {
        Consumer consumer = getConsumerByWechatOpenid(consumerToken.getOpenid());
        if (consumer == null) {
            return new ApiVO(ApiVO.STATUS.F, "获取用户信息失败");
        }
        if (b && DictRegTypeConsts.DICT_WECHAT.equals(consumer.getRegType())) {
            //如果是微信注册的，登录的同时更新用户信息
            updateConsumer(consumer, consumerToken);
        }
        return new ApiVO(ApiVO.STATUS.S).setObject1(consumer);
    }


    /**
     * 第三方登录 微博登录
     *
     * @param consumerToken 登录信息
     * @param b
     * @return
     */
    public ApiVO checkOpenid4Weibo(ConsumerToken consumerToken, boolean b) {
        Consumer consumer = getConsumerByWeiboOpenid(consumerToken.getOpenid());
        if (consumer == null) {
            return new ApiVO(ApiVO.STATUS.F, "获取用户信息失败");
        }
        if (b && DictRegTypeConsts.DICT_WEIBO.equals(consumer.getRegType())) {
            //如果是微博注册的，登录的同时更新用户信息
            updateConsumer(consumer, consumerToken);
        }
        return new ApiVO(ApiVO.STATUS.S).setObject1(consumer);
    }


    public Consumer getConsumerByQQOpenid(String openid) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("qq_id", openid);
        Consumer consumer = consumerMapper.selectOne(queryWrapper);
        return consumer;
    }

    public Consumer getConsumerByWechatOpenid(String openid) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("wechat_id", openid);
        Consumer consumer = consumerMapper.selectOne(queryWrapper);
        return consumer;
    }

    public Consumer getConsumerByWeiboOpenid(String openid) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("weibo_id", openid);
        Consumer consumer = consumerMapper.selectOne(queryWrapper);
        return consumer;
    }


    /**
     * 更新基本信息
     *
     * @param consumer
     * @param consumerToken
     */
    private void updateConsumer(Consumer consumer, ConsumerToken consumerToken) {
        boolean b1, b2, b3;
        b1 = b2 = b3 = true;
        if (StrUtil.isNotEmpty(consumerToken.getNickname())) {
            if (consumerToken.getNickname().equals(consumer.getNickname())) {
                b1 = false;
            }
            consumer.setNickname(consumerToken.getNickname());
        }
        b2 = setHeader(consumerToken, consumer, true);
        b3 = setSex(consumerToken, consumer);
        if (!b1 && !b2 && !b3) {
            return;
        }
        LambdaUpdateWrapper<Consumer> queryWrapper = new UpdateWrapper<>(new Consumer()).lambda();
        try {
            queryWrapper.set(Consumer::getNickname, consumer.getNickname())
                    .set(Consumer::getHead, consumer.getHead())
                    .set(Consumer::getSex, consumer.getSex())
                    .eq(Consumer::getConsumerId, consumer.getConsumerId());
            consumerMapper.update(new Consumer(), queryWrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置头像->上传网络图片到OSS
     *
     * @param consumerToken
     * @param consumer
     * @param isUpdate
     */
    private boolean setHeader(ConsumerToken consumerToken, Consumer consumer, boolean isUpdate) {
        if (StrUtil.isNotEmpty(consumerToken.getHead())) {
            if (isUpdate) {
                if (consumerToken.getHead().equals(consumer.getSourceHead())) {
                    //未改变头像
                    return false;
                }
            } else {
                consumer.setSourceHead(consumerToken.getHead());
            }
            //应该上传至OSS后返回OSS地址
            consumer.setHead(consumerToken.getHead());
//            try {
//                consumer.setHead(putOss4Head(null, consumerToken.getHead()));
//            } catch (ImageException e) {
//                return false;
//            }
        }
        return true;
    }

    /**
     * 设置性别
     *
     * @param consumerToken
     * @param consumer
     */
    private boolean setSex(ConsumerToken consumerToken, Consumer consumer) {
        if (StrUtil.isNotEmpty(consumerToken.getSex())) {
            if ("1".equals(consumerToken.getSex()) && !"男".equals(consumer.getSex())) {
                consumer.setSex("男");
                return true;
            } else if ("2".equals(consumerToken.getSex()) && !"女".equals(consumer.getSex())) {
                consumer.setSex("女");
                return true;
            }
        }
        return false;
    }

}
