aliyun MNS 消息队列 使用指南
-------------
* 本项目依赖不向下传递
* 开通请参考[阿里云-消息服务-MNS](https://help.aliyun.com/product/27412.html?spm=5176.7944397.207973.oss4.4d1ab2418oQPP0)

> 引入依赖 pom.xml

```mxml
 <dependencies>
     <!-- 阿里云基础SDK -->
     <dependency>
         <groupId>com.aliyun</groupId>
         <artifactId>aliyun-java-sdk-core</artifactId>
     </dependency>
     <!-- 阿里云消息服务MNS相关SDK -->
     <dependency>
         <groupId>com.aliyun.mns</groupId>
         <artifactId>aliyun-sdk-mns</artifactId>
         <classifier>jar-with-dependencies</classifier>
     </dependency>
 </dependencies>
```
> 配置
```
#mns
yexuejc.alibaba.mns.access-key-id=阿里MNS提供的AccessKey
yexuejc.alibaba.mns.access-key-secret=阿里MNS提供的的SecretKey
yexuejc.alibaba.mns.endpoint=MNS接入endpoint，公网，私网，VPC各不相同，需要分环境配置
yexuejc.alibaba.mns.queue-name-prefix=队列名称前缀
```

> 使用example

参考[MnsTest.java](../yexuejc-springboot-base/src/test/java/com/yexuejc/springboot/base/test/MnsTest.java)
