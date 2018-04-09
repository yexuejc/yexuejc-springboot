# yexuejc-springboot-base

基于springboot maven 封装可继承基础工程

### 引用
>yexuejc.springboot.version=1.0.3

pom.xml
```
<dependencies>
    <dependency>
        <groupId>com.github.yexuejc</groupId>
        <artifactId>yexuejc-springboot</artifactId>
        <version>${yexuejc.springboot.version}</version>
    </dependency>
</dependencies>
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```


### 目录
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