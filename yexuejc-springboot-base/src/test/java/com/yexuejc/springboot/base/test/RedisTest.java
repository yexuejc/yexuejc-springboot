package com.yexuejc.springboot.base.test;

import com.yexuejc.base.util.JsonUtil;
import com.yexuejc.springboot.base.ApplicationRun;
import com.yexuejc.springboot.base.autoconfigure.MutiRedisAutoConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @author maxf
 * @PackageName com.yexuejc.springboot.base.test
 * @Description
 * @date 2018/9/26 16:28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationRun.class)
public class RedisTest {
    @Autowired
    @Qualifier(MutiRedisAutoConfiguration.BEAN_REDIS_TEMPLATE0)
    RedisTemplate redisTemplate;

    @Test
    public void insert() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", "b");
        map.put("c", 1);
        map.put("aac", true);
        map.put("q", "asdb");
        redisTemplate.opsForHash().putAll("test.redis", map);
        redisTemplate.expire("test.redis", 10, TimeUnit.MINUTES);
    }

    @Test
    public void get() {
        Map<String, Object> map = redisTemplate.opsForHash().entries("test.redis");
        System.out.println(JsonUtil.obj2Json(map));
    }

    @Test
    public void update() {
        Map<String, Object> map = redisTemplate.opsForHash().entries("test.redis");
        redisTemplate.opsForHash().put("test.redis", "q", "123456798");
        System.out.println(JsonUtil.obj2Json(map));
    }

    @Test
    public void del() {
        Boolean delete = redisTemplate.delete("test.redis");
        System.out.println(delete);
    }
}
