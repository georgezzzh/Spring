## Response

* <a href="#xml">响应xml</a>
* <a href="#json">响应json</a>
* <a href="#async">异步响应</a>

### @RestController

通过直接在控制器标注新的@RestController, 就不需要在每个@RequestMapping方法添加@ResponseBody了，标注的@RestController的类，无法返回视图。

<h3 id="xml">1. SpringMVC返回xml数据</h3>

```java
   @RequestMapping(path="/handle",produces = "application/xml;charset=UTF-8")
    public   @ResponseBody String handle(){
        User user=new User();
        user.setUserName("george");
        user.setRealName("zhouzz");
        user.setPassword("123456");
        XStream xStream=new XStream();
        xStream.alias("user",User.class);
        String result=xStream.toXML(user);
        return result;
    }
```
输出xml的Maven依赖
```xml
   <dependency>
      <groupId>com.thoughtworks.xstream</groupId>
      <artifactId>xstream</artifactId>
      <version>1.4.10</version>
    </dependency>
```

<h3 id="json">2. SpringMVC响应json数据</h3>

```java
//通过ResponseBody响应json数据
    @RequestMapping(value = "/blog",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public @ResponseBody String getBlog(@RequestParam String username){
        //String是用Gson格式化后的json字符串
        String data=blogService.getAllBlogByUsername(username);
        return data;
    }
```

<h3 id="async">3. 异步响应</h3>

1. 在web.xml中增加对异步的支持

   web.xml必须时3.0以上的版本才支持async

   ```xml
   <servlet>
     <servlet-name>dispatcher</servlet-name>
     <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
     <load-on-startup>1</load-on-startup>
      <!--异步的支持-->
     <async-supported>true</async-supported>
   </servlet>
   <!--所有的filter必须全都实现异步的支持-->
   <filter>
       <filter-name>encodingFilter</filter-name>
       <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
       <init-param>
         <param-name>encoding</param-name>
         <param-value>UTF-8</param-value>
       </init-param>
       <init-param>
         <param-name>forceEncoding</param-name>
         <param-value>true</param-value>
       </init-param>
       <async-supported>true</async-supported>
     </filter>
   ```

2. 异步响应
        
    ```java
      @RequestMapping("/api")
      public Callable<User> api(){
          System.out.println("hello");
          return new Callable<User>() {
              @Override
              public User call() throws Exception {
                  //三秒之后返回响应
                  Thread.sleep(3000);
                  User user=new User();
                  user.setUserName("www");
                  return user;
              }
          };
      }
    ```