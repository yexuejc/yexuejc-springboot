# yexuejc-springboot-simple

### 说明
>本工程是yexuejc-springboot的使用示例，新接入请参考本工程

#### 什么都不用
<details>
<summary>直接新建一个springboot工程 pom.xml 配置</summary>

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.yexuejc.springboot</groupId>
        <artifactId>yexuejc-springboot-parent</artifactId>
        <version>1.2.1</version>
    </parent>
    <groupId>top.yexuejc</groupId>
    <artifactId>yexuejc-springboot-simple</artifactId>
    <version>0.0.1</version>
    <name>yexuejc-springboot-simple</name>
    <description>测试工程</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>com.yexuejc.springboot</groupId>
            <artifactId>yexuejc-springboot-base</artifactId>
            <version>1.2.1</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>

```
</details>

#### 使用多端登录
参考[使用多端登录](../springboot-security-login-simple/README.md)
