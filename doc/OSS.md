aliyun OSS 对象存储 使用指南
-------------
* 本项目依赖不向下传递
* 开通请参考[阿里云对象存储-OSS](https://help.aliyun.com/document_detail/32008.html?spm=5176.87240.400427.45.1dd74614DiKpxR)

_本工程暂提供上传功能_

> 引入依赖 pom.xml

```mxml
 <dependencies>
   <!-- 阿里云基础SDK -->
   <dependency>
       <groupId>com.aliyun</groupId>
       <artifactId>aliyun-java-sdk-core</artifactId>
   </dependency>
   <!-- 阿里云OSS相关SDK -->
   <dependency>
       <groupId>com.aliyun.oss</groupId>
       <artifactId>aliyun-sdk-oss</artifactId>
   </dependency>
 </dependencies>
```
> 配置
```
#OSS
yexuejc.alibaba.oss.endpoint=阿里OSS提供的endpoint
yexuejc.alibaba.oss.access-key-secret=阿里OSS提供的SecretKey
yexuejc.alibaba.oss.access-key-id=阿里OSS提供的AccessKey
yexuejc.alibaba.oss.bucket=阿里OSS提供的bucket

```

> 使用example

参考[OssTest.java](../yexuejc-springboot-base/src/test/java/com/yexuejc/springboot/base/test/OssTest.java)
