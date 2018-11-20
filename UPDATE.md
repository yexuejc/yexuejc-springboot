yexuejc-springboot 更新内容
-------------------

#### version ：2.0.3
**time：2018-11-9 16:58:06** <br/>
**branch：** master    <br/>
**关联工程：**    <br/>
```
springboot-base:1.2.1
spring-boot-starter-parent:2.0.5.RELEASE
```
**update：**     <br/>
1. 集成springboot security 多方登录
2. 文档拆分


#
#### version ：2.0.2
**time：2018-10-27 16:42:08** <br/>
**branch：** master    <br/>
**关联工程：**    <br/>
```
springboot-base:1.2.0
spring-boot-starter-parent:2.0.5.RELEASE
```
**update：**     <br/>
1. 日志优化，按天切割日志<br>
内置 <br>
LogUtil.accessLogger.info(xxxx);请求访问控制，级别info[LogInterceptor->line39](com.yexuejc.springboot.base.interceptor.LogInterceptor)<br>
LogUtil.bizLogger.info(xxxx); 业务日志，级别trace<br>
LogUtil.exceptionLogger.error(xxxx);异常日志，级别error<br>
```$xslt
日志配置置于application.properties
详情参考 logback-spring.xml

#日志contextName
spring.application.name=@pom.artifactId@
#日志级别
logging.level.root=info
#日志输出目录
logging.path=/logs/yexuejc-springboot-parent

```
#

#### version ：2.0.1
**time：2018-9-28 15:25:30** <br/>
**branch：** 2.x    <br/>
**关联工程：**    <br/>
```
springboot-base:1.1.9
spring-boot-starter-parent:2.0.5.RELEASE
```
**update：**     <br/>
1. 新增声明包
#

#### version ：2.0.0
**time：2018-9-26 16:55:00** <br/>
**branch：** 2.x    <br/>
**关联工程：**    <br/>
```
springboot-base:1.1.9
spring-boot-starter-parent:2.0.5.RELEASE
```
**update：**     <br/>
1. 升级依赖
2. 2.x分支将基于springboot 2.x 开发
#

#### version ：1.1.0
**time：2018-9-23 12:49:36** <br/>
**branch：** master    <br/>
**关联工程：**    <br/>
```
springboot-base:1.1.9
spring-boot-starter-parent:1.5.15.RELEASE
```
**update：**     <br/>
1. 升级依赖
2. 增加SSL证书忽略：默认关闭
3. 本版本更新工具依赖[https://github.com/yexuejc/yexuejc-base.git](https://github.com/yexuejc/yexuejc-base.git)不向下兼容
#

#### version ：1.0.15
**time：2018-9-3 19:29:39** <br/>
**branch：** master    <br/>
**关联工程：**    <br/>
```
springboot-base:1.1.8
spring-boot-starter-parent:1.5.15.RELEASE
```
**update：**     <br/>
1. 升级依赖
#

#### version ：1.0.14
**time：2018-8-25 14:31:05** <br/>
**branch：** master    <br/>
**关联工程：**    <br/>
```
springboot-base:1.1.7
spring-boot-starter-parent:1.5.15.RELEASE
```
**update：**     <br/>
1. 升级依赖
2. 优化拦截规则：增加通配符 /**
#

#### version ：1.0.13
**time：2018-8-17 11:41:18** <br/>
**branch：** master    <br/>
**关联工程：**    <br/>
```
springboot-base:1.1.7
```
**update：**     <br/>
1. 升级base
#

#### version ：1.0.12
**time：2018-6-19 22:18:03** <br/>
**branch：** master    <br/>
**关联工程：**    <br/>
```
springboot-base:1.1.6
```
**update：**     <br/>
1. maven仓库更新

#
#### version ：1.0.11
**time：2018-6-19 22:18:03** <br/>
**branch：** master    <br/>
**update：**     <br/>
1. 更新springboot-base:1.1.5依赖
#

#### version ：1.0.10
**time：2018年6月14日22:31:18** <br/>
**branch：** master    <br/>
**update：**     <br/>
1. 更新springboot-base:1.1.4依赖
2. 统一编码：UTF-8
#

#### version ：1.0.9
**time：2018年6月2日12:17:18** <br/>
**branch：** master    <br/>
**update：**     <br/>
1. 更新base依赖
#

#### version ：1.0.8
**time：** 2018-5-4 09:54:18<br/>
**branch：** master    <br/>
**update：**     <br/>
1. 修复依赖
2. 使用加密：配置密钥方式/配置密钥方式 二选一
```
#加密开关
yexuejc.http.encrypt.encrypt=true
yexuejc.http.encrypt.decrypt=true
#配置密钥方式
yexuejc.http.encrypt.private-key=私钥
#配置证书方式
yexuejc.http.encrypt.private-key-path=/lgfishing.keystore 路径
yexuejc.http.encrypt.private-alias=别名
yexuejc.http.encrypt.private-pwd=密码
```

#
##### version ：1.0.7
**time：** 2018-5-4 09:54:18<br/>
**branch：** master    <br/>
**update：**     <br/>
>1. 新增加密证书配置
>2. RSA迁移到[yexuejc-base:1.1.1](https://github.com/yexuejc/yexuejc-base)工程 

#
##### version ：1.0.6 ~~1.0.5~~
**【change:2018-5-15 09:24:37】** 1.0.5 jitpack打包失败，升级版本1.0.6<br/>
**time：** 2018-5-4 09:54:18<br/>
**branch：** master    <br/>
**update：**     <br/>
> [使用加密解密](doc/PARAMS_RSA_DECRYPT_ENCRYPT.md)
>
>1.增加json入参解密、出参加密
#

##### version ：1.0.4
**time：** 2018-5-4 09:54:18<br/>
**branch：** master    <br/>
**update：**     <br/>
>1.更新springboot至1.5.12.RELEASE
#
#### version ：1.0.3
**time：** 2018-4-9 15:24:13<br/>
**branch：** master    <br/>
**update：**     <br/>
>1.变更包名
#
#### version ：1.0.2
**time：** 2018-4-1 17:00:15<br/>
**env：** prod    <br/>
**update：**     <br/>
>1.修复工具包ApiVO
#
#### version ：0.0.2
**time：** 2018-1-31 13:48:34<br/>
**env：** ivt    <br/>
**update：**     <br/>
>1.集成日志【security】
#

##### version ：0.0.1
**time：** 2018-1-31 12:16:10<br/>
**env：** ivt    <br/>
**update：**     <br/>
>1.第一次上传，集成通用环境

#
