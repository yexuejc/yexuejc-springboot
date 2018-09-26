# yexuejc-springboot

### 说明
>基于springboot版本分别维护1.x 2.x 分支 <br>
>master将继续同步1.x分支（后期获取会同步于2.x）


#### 项目介绍
基于springboot maven 封装可继承基础工程

内含parent和base工程可分开使用<br/>
parent：版本封装<br/>
base:功能封装

#### 最新版本
>yexuejc.springboot.version=1.1.1 <br>
>yexuejc.base.version=1.1.9

pom.xml
```
<!--parent 引用-->
<parent>
    <groupId>com.yexuejc.springboot</groupId>
    <artifactId>yexuejc-springboot-parent</artifactId>
    <version>最新版本</version>
</parent>

<dependencies>
    <!--base 引用-->
    <dependency>
        <groupId>com.yexuejc.springboot</groupId>
        <artifactId>yexuejc-springboot-base</artifactId>
        <version>${parent.version}</version>
    </dependency>
    <!--推荐使用-->
    <!--https://gitee.com/incloudcode/yexuejc-base.git-->
    <dependency>
        <dependency>
            <groupId>com.yexuejc.base</groupId>
            <artifactId>yexuejc-base</artifactId>
            <version>${yexuejc.base.version}</version>
        </dependency>
    </dependency>
</dependencies>
<repositories>
    <repository>
        <id>yexuejc-nexus-public</id>
        <name>yexuejc-nexus-public</name>
        <url>https://nexus.yexuejc.club/repository/maven-public/</url>
    </repository>
</repositories>
```


#### 目录
<table>
    <tr>
        <td>\src\main\java</td>
        <td>核心代码</td>
    </tr>
    <tr>
        <td>\src\main\resources</td>
        <td>核心配置</td>
    </tr>
    <tr>
        <td>com.yexuejc.springboot.base.autoconfigure.*</td>
        <td>模块封装</td>
    </tr>
    <tr>
        <td>com.yexuejc.springboot.base.constant.*</td>
        <td>系统常量</td>
    </tr>
    <tr>
        <td>com.yexuejc.springboot.base.filter.*</td>
        <td>过滤器</td>
    </tr>
    <tr>
        <td>com.yexuejc.springboot.base.interceptor.*</td>
        <td>拦截器</td>
    </tr>
    <tr>
        <td>com.yexuejc.springboot.base.http.*</td>
        <td>网络层</td>
    </tr>
    <tr>
        <td>com.yexuejc.springboot.base.pojo.*</td>
        <td>bean</td>
    </tr>
    <tr>
        <td>com.yexuejc.springboot.base.util.*</td>
        <td>工具包</td>
    </tr>
    <tr>
        <td>\src\test\</td>
        <td>测试部分</td>
    </tr>
    <tr>
        <td>com.yexuejc.springboot.base.ApplicationRun</td>
        <td>测试启动入口</td>
    </tr>
</table>


#### 版本更新

[更新记录](UPDATE.md)
<br/>
[1.0.6新增 针对API请求安全解决方案](PARAMS_RSA_DECRYPT_ENCRYPT.md)<br/>
[1.0.6新增 加密功能](PARAMS_RSA_DECRYPT_ENCRYPT.md)
