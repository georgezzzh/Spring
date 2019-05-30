## web.xml中配置Spring应用

### Spring中配置ContextConfigLocation

当注册了监听器时

 ```xml
  <listener>
  　　　　<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>
 ```

监听时，Spring默认会查找/WEB-INF/applicationContext.xml文件。也可以用`<context-param></context-param>`来自定义配置。

```xml
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>/WEB-INF/smart-context.xml</param-value>
</context-param>
```

如果有多个配置文件，可以用逗号隔开，如下

```xml
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>/WEB-INF/smart-context.xml,/WEB-INF/smart-service.xml</param-value>
</context-param>
```

### 配置SpringMVC的Dispatcher

```xml
<!--配置SpringMVC-->
<servlet>
  <servlet-name>smart</servlet-name>
  <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
</servlet>
<servlet-mapping>
  <servlet-name>smart</servlet-name>
  <url-pattern>/</url-pattern>
</servlet-mapping>
```

需要注意的是url-pattern，写的是`<url-pattern>/</url-pattern>`,如果写为`<url-pattern>/*</url-pattern>`时，会把静态资源也拦截了。静态资源拦截SpringMVC中没有配置对应的控制器会报错。

配置Dispatcher时，Dispatcher文件，应该名为xxx-servlet.xml，其中servlet-name即为xxx；
### 把全部的配置文件放在classpath下
对应的IDEA的Maven工程，放在resource目录下，resource下的文件，编译后会在classpath下。
applicationContext.xml在web.xml中如下配置
```xml
  <!--配置spring的应用上下文，初始化Bean-->
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath*:applicationContext.xml</param-value>
  </context-param>
  <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>
```
响应的mvc-dispatcher.xml配置如下
```xml
<!--配置SpringMVC,放在resources下-->
  <servlet>
    <servlet-name>mvc</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath*:mvc-servlet.xml</param-value>
    </init-param>
  </servlet>
  <servlet-mapping>
    <servlet-name>mvc</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>
```

如果IDEA始终配置无效时，并且控制台也不报错，再xml配置文件中故意写错误的xml标记，控制台也不报错，可以将IDEA关闭重新启动，再运行。
