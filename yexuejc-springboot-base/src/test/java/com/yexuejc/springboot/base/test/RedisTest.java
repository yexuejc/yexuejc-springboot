package com.yexuejc.springboot.base.test;

import com.yexuejc.base.util.JsonUtil;
import com.yexuejc.base.util.StrUtil;
import com.yexuejc.springboot.base.autoconfigure.MutiRedisAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis 操作
 *
 * @author maxf
 * @version 1.0
 * @ClassName RedisTest
 * @Description
 * @date 2018/11/1 11:16
 */
@SpringBootTest
public class RedisTest {
    /**
     * 指向redis 0库
     */
    @Autowired
    @Qualifier(MutiRedisAutoConfiguration.BEAN_REDIS_TEMPLATE0)
    private RedisTemplate<Object, Object> redisTemplate0;

    /**
     * 保存用户登录信息
     * name:张三
     */
    @Test
    public void save() {
        redisTemplate0.afterPropertiesSet();
        redisTemplate0.opsForHash().put("login.user.userId1", "name", "张三");
        redisTemplate0.expire("login.user.userId1", 10, TimeUnit.MINUTES);
    }

    /**
     * 保存用户登录信息
     * name:张三
     * sex:男
     */
    @Test
    public void save2() {
        redisTemplate0.afterPropertiesSet();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("sex", "男");
        redisTemplate0.opsForHash().putAll("login.user.userId1", map);
        redisTemplate0.expire("login.user.userId1", 10, TimeUnit.MINUTES);
    }

    /**
     * 保存授权zhangsan的token,过期时间10分钟
     */
    @Test
    public void accessToken() {
        redisTemplate0.afterPropertiesSet();
        redisTemplate0.opsForValue().append("zhangsan.access_token", StrUtil.genUUID());
        redisTemplate0.expire("zhangsan.access_token", 10, TimeUnit.MINUTES);
    }


    /**
     * 获取用户登录信息
     * name:张三
     */
    @Test
    public void get() {
        redisTemplate0.afterPropertiesSet();
        String name = (String) redisTemplate0.opsForHash().get("login.user.userId1", "name");
        System.out.println(name);
    }

    /**
     * 修改用户登录信息
     * name:张三->李四
     * age:20
     */
    @Test
    public void put() {
        redisTemplate0.afterPropertiesSet();
        redisTemplate0.opsForHash().put("login.user.userId1", "name", "张三");
        redisTemplate0.opsForHash().put("login.user.userId1", "age", "20");
        Map<String, Object> map = (Map) redisTemplate0.opsForHash().entries("login.user.userId1");
        System.out.println(JsonUtil.obj2Json(map));
    }

    /**
     * 删除
     */
    @Test
    public void del() {
        redisTemplate0.afterPropertiesSet();
        redisTemplate0.delete("login.user.userId1");
    }

}
