## log4j2 与 SLF4j

[日志demo]([https://github.com/georgezhou314/Spring/tree/master/%E6%97%A5%E5%BF%97/TomcatHots](https://github.com/georgezhou314/Spring/tree/master/日志/TomcatHots))

### 1. 日志的Maven依赖包

```xml
 <!--日志相关的组件-->
<!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core -->
<dependency>
  <groupId>org.apache.logging.log4j</groupId>
  <artifactId>log4j-core</artifactId>
  <version>2.11.2</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api -->
<dependency>
  <groupId>org.apache.logging.log4j</groupId>
  <artifactId>log4j-api</artifactId>
  <version>2.11.1</version>
</dependency>
<!-- Web项目需添加 -->
<dependency>
  <groupId>org.apache.logging.log4j</groupId>
  <artifactId>log4j-web</artifactId>
  <version>2.9.1</version>
</dependency>
<!--log4j用于与slf4j保持桥接-->
<dependency>
  <groupId>org.apache.logging.log4j</groupId>
  <artifactId>log4j-slf4j-impl</artifactId>
  <version>2.9.1</version>
</dependency>
<!-- slf4j核心包-->
<dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-api</artifactId>
  <version>1.7.25</version>
</dependency>
<!--日志相关结束-->
```

### 2.web.xml配置

放在resources目录下不需要在web.xml中配置

### 3. log4j2.xml配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration status="debug">
    <appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <ThresholdFilter level="trace" onMatch="ACCEPT" onMismatch="DENY"/>
            <PatternLayout pattern="%d{yyyy-MM--dd HH:mm:ss.SSS} %t [%p] %c (%F:%L) %msg%n"/>
        </Console>
        <!--${sys:catalina.home}是系统的环境变量CATALINA_HOME的值，fileName指定日志文件存放的路径-->
        <RollingFile name="RollingFileInfo" fileName="${sys:catalina.home}/webapps/TomcatHots/logs/info.log"
                     filePattern="${sys:catalina.home}/webapps/TomcatHots/logs/info-%d{yyyy-MM-dd}.log">
            <ThresholdFilter level="info" onMatch="ACCEPT" onMismatch="DENY"/>
            <PatternLayout pattern="[%d{HH:mm:ss.SSS}] [%p] - %l - %m%n"/>
            <Policies>
                <TimeBasedTriggeringPolicy/>
                <SizeBasedTriggeringPolicy size="100 MB"/>
            </Policies>
            <DefaultRolloverStrategy max="20"/>
        </RollingFile>
    </appenders>
    <loggers>
        <Root level="info">
            <AppenderRef ref="RollingFileInfo"/>
            <AppenderRef ref="Console"/>
        </Root>
    </loggers>
</configuration>
```

### 4. HelloController.java

```java
package com.sonydafa.linux;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
@Controller
public class HelloController {
    //用flf4j日志工厂，获取日志
    private static final Logger logger= LoggerFactory.getLogger(HelloController.class);
    @RequestMapping("/hello")
    public String hello(HttpServletRequest request){
        String ip=request.getRemoteAddr();
        logger.info("uri {}","/hello");
        logger.info("ip: {}",ip);
        return "hello";
    }
    @RequestMapping("/")
    public @ResponseBody String defaultPath(){
        logger.info("uri {}","/");
        return "default path";
    }
}
```

### 5.项目路径

```
├─java
│  └─com
│      └─sonydafa
│          └─linux
│                  HelloController.java
│
├─resources
│      applicationContext.xml
│      log4j2.xml
│      mvc-servlet.xml
│
└─webapp
    └─WEB-INF
        │  web.xml
        │
        └─templates
                hello.html
```

