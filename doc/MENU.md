功能目录
------------------------

### 第三方集成 -> [第三方集成具体使用](plugin/AutoConfigure.md)
#
* [redis 集成](REDIS.md)
* [Aliyun MNS 消息队列](MNS.md)
* [Aliyun OSS 对象存储](OSS.md)

### 内部集成
#
* [(2.0.3-2.0.5)新增 集成security多方登录](SECURITY.md)
单独使用例子工程：[https://github.com/yexuejc/springboot-security-login-simple](https://github.com/yexuejc/springboot-security-login-simple)
* [1.0.6新增 针对API请求安全解决方案](PARAMS_RSA_DECRYPT_ENCRYPT.md)<br/>
* [1.0.6新增 加密功能](PARAMS_RSA_DECRYPT_ENCRYPT.md)


### 运行错误
1. jdk版本问题，请切换jdk为1.8版或者jvm增加启动参数`--add-opens java.base/java.util=ALL-UNNAMED`
``` 
Cannot load configuration class: com.example.demo.DemoApplication
Unable to make protected final java.lang.Class java.lang.ClassLoader.defineClass(java.lang.String,byte[],int,int,java.security.ProtectionDomain) throws java.lang.ClassFormatError accessible: module java.base does not "opens java.lang" to unnamed module @5af3afd9
```