本项目自动装配开关
---------------------

## 说明
本项目从2.1.0开始所有自动装配的功能增加开关控制，默认插件功能关闭.
开启需要在使用项目`application.properties`中增加`yexuejc.autoconfigure.mns.enable=true`类似的配置（每一个插件功能key不一样）

### 插件功能
注：使用阿里系的功能请引入阿里基础包
```
<!-- 阿里云基础SDK -->
<dependency>
    <groupId>com.aliyun</groupId>
    <artifactId>aliyun-java-sdk-core</artifactId>
    <optional>true</optional>
</dependency>
```

#### 阿里云MNS功能
> 类：[MnsAutoConfiguration](../../yexuejc-springboot-base/src/main/java/com/yexuejc/springboot/base/autoconfigure/MnsAutoConfiguration.java)
> <br/> 功能开关：`yexuejc.autoconfigure.mns.enable=true`
> <br/> 依赖：`MNSClient.class`
> <br/> pom 引入
> ```
> <!-- 阿里云消息服务MNS相关SDK -->
> <dependency>
>     <groupId>com.aliyun.mns</groupId>
>     <artifactId>aliyun-sdk-mns</artifactId>
>     <classifier>jar-with-dependencies</classifier>
>     <optional>true</optional>
> </dependency>
> ```
>application.properties 配置
>```
> yexuejc.alibaba.mns.access-key-id=自己到阿里云申请access-key
> yexuejc.alibaba.mns.access-key-secret=自己到阿里云申请access-key-secret
> yexuejc.alibaba.mns.endpoint=自己阿里云的endpoint
> yexuejc.alibaba.mns.queue-name-prefix=mns前缀
>```

#### redis 多database 配置
> 类：[MutiRedisAutoConfiguration](../../yexuejc-springboot-base/src/main/java/com/yexuejc/springboot/base/autoconfigure/MutiRedisAutoConfiguration.java)
> <br/> 功能开关：`yexuejc.autoconfigure.redis.enable=true`
> <br/> 依赖：`JedisConnection.class`, `RedisOperations.class`, `Jedis.class`
> <br/> pom 引入
> ```
> <!-- 使用Redis -->
> <dependency>
>     <groupId>org.springframework.data</groupId>
>     <artifactId>spring-data-redis</artifactId>
>     <optional>true</optional>
> </dependency>
> <dependency>
>     <groupId>redis.clients</groupId>
>     <artifactId>jedis</artifactId>
>     <optional>true</optional>
> </dependency>
> ```
>application.properties 配置
><br/> 默认db0k开启 1-15需要使用哪个`yexuejc.redis.dbx=true`
>```
> yexuejc.redis.db1=true
> spring.redis.jedis.pool.max-active=100
> spring.redis.jedis.pool.max-idle=10
> spring.redis.jedis.pool.min-idle=3
> spring.redis.host=地址
> spring.redis.password=密码
> spring.redis.port=端口
>```

#### 阿里 OSS 功能 
> 类：[OssAutoConfiguration](../../yexuejc-springboot-base/src/main/java/com/yexuejc/springboot/base/autoconfigure/OssAutoConfiguration.java)
> <br/> 功能开关：`yexuejc.autoconfigure.oss.enable=true`
> <br/> 依赖：`OSSClient`
> <br/> pom 引入
> ```
> <!-- 阿里云OSS相关SDK -->
> <dependency>
>     <groupId>com.aliyun.oss</groupId>
>     <artifactId>aliyun-sdk-oss</artifactId>
>     <optional>true</optional>
> </dependency>
> ```
>application.properties 配置
>```
> yexuejc.alibaba.oss.endpoint=自己去阿里申请的endpoint
> yexuejc.alibaba.oss.access-key-secret=自己到阿里云申请access-key-secret
> yexuejc.alibaba.oss.access-key-id=自己到阿里云申请access-key
> yexuejc.alibaba.oss.bucket=自己去阿里申请的bucket
>```

#### WebMvc 相关配置
> 类：[WebAutoConfiguration](../../yexuejc-springboot-base/src/main/java/com/yexuejc/springboot/base/autoconfigure/WebAutoConfiguration.java)
> <br/> 功能开关：`yexuejc.autoconfigure.webmvc.enable=true`
> 本类开启后的具体使用说明参见[WebMvc相关配置](WebAutoConfiguration.md)
