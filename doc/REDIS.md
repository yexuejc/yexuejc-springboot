redis 使用指南
-------------
* 本项目依赖不向下传递

> 引入依赖 pom.xml

```mxml
 <dependencies>
    <!-- 使用Redis -->
    <dependency>
        <groupId>org.springframework.data</groupId>
        <artifactId>spring-data-redis</artifactId>
    </dependency>
    <dependency>
        <groupId>redis.clients</groupId>
        <artifactId>jedis</artifactId>
    </dependency>
 </dependencies>
```
> 配置
```
#reids
spring.redis.host=你的reids地址
spring.redis.password=密码
spring.redis.port=端口
```

> 使用example

参考[RedisTest.java](../yexuejc-springboot-base/src/test/java/com/yexuejc/springboot/base/test/RedisTest.java)
