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

