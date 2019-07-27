package com.example.demo.web;

import com.yexuejc.base.http.Resps;
import com.yexuejc.base.pojo.ApiVO;
import com.yexuejc.base.util.RegexUtil;
import com.yexuejc.base.util.StrUtil;
import com.yexuejc.springboot.base.autoconfigure.MutiRedisAutoConfiguration;
import com.yexuejc.springboot.base.constant.BizConsts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * Security 登录注册相关controller
 * 主要实现
 * 1.短信登录发送验证码
 * 2.第三方登录绑定手机号（以及绑定手机号发送验证码）
 * </pre>
 *
 * @author maxf
 * @version 1.0
 * @ClassName SecurityCtrl
 * @Description
 * @date 2018/11/9 10:52
 */
@RestController
public class SecurityCtrl {

    @Autowired
    @Qualifier(MutiRedisAutoConfiguration.BEAN_REDIS_TEMPLATE1)
    RedisTemplate<Object, Object> redisTemplate;

    /**
     * 登录发送短信
     *
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/login/{mobile}", method = RequestMethod.POST)
    public Resps login(@PathVariable String mobile) {
        if (!RegexUtil.regex(mobile, RegexUtil.REGEX_MOBILE)) {
            return Resps.fail("手机号不正确");
        }
        ApiVO apiVO = sendSmsCode(BizConsts.CONSUMER_LOGIN_SMS, mobile);
        if (apiVO.isFail()) {
            return Resps.fail(apiVO.getMsg());
        }
        return Resps.success(apiVO.getMsg());
    }

    private ApiVO sendSmsCode(String smsType, String mobile) {
        String smsId = StrUtil.genUUID(30);
        String code = StrUtil.genNum().substring(2, 8);
        //自己接入短信发送
        boolean result = true;
        if (result) {
            //成功
            //存reids
            Map<String, Object> map = new HashMap<>();
            map.put("smsId", smsId);
            map.put("code", code);
            map.put("trade_id", "短信返回id");
            map.put("validatedNums", 0);
            redisTemplate.afterPropertiesSet();
            redisTemplate.opsForHash().putAll(smsType + "." + mobile, map);
            // 过期时间：5分钟
            redisTemplate.expire(smsType + "." + mobile, 5 * 60, TimeUnit.SECONDS);
            return new ApiVO(ApiVO.STATUS.S, "短信发送成功");
        } else {
            return new ApiVO(ApiVO.STATUS.F, "短信发送失败");
        }
    }

}
