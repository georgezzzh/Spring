## SpringMVC 文件上传

**Attention: 使用HttpServletRequest时，一定要设置UTF-8编码**
### 1. 使用Spring自带的StandardServletMultipartResolver解析

1. 依赖包(配置好Spring的依赖包就可以，不需要另外的包)

2. web.xml配置

   要求web-app的vesion>=3.0，因为Spring的StandardMultipartResolver需要依赖Servlet-api, 所以要在web.xml配置

   ```xml
    <!--配置SpringMVC-->
     <servlet>
       <servlet-name>dispatcher</servlet-name>
       <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
       <load-on-startup>1</load-on-startup>
       <async-supported>true</async-supported>
       <!--multipart-file配置-->
       <multipart-config>
         <location>C:\Users\geoge\Desktop</location>
         <max-file-size>1000000</max-file-size>
       </multipart-config>
     </servlet>
   ```

3. dispatcher-servlet.xml配置

   ```xml
   <!--配置标准multipart-->
   <bean id="multipartResolver" class="org.springframework.web.multipart.support.StandardServletMultipartResolver"/>
   ```

4. java代码

   ```java
   @RequestMapping("/user/upload")
   //这里用的是MultipartHttpServletRequest
   public String uploadBySpring(MultipartHttpServletRequest request) throws Exception{
       MultipartFile file=request.getFile("file");
       String name=request.getParameter("name");
       file.transferTo(new File("C:\\Users\\geoge\\Desktop\\"+file.getOriginalFilename()));
       return "filename="+file.getOriginalFilename()+",name="+name;
   }
   ```

5. 前端表单一样


### 2.  使用apache.common方式上传

1. 依赖包(commons-fileupload, commons-io)两个jar包

3. dispattcher-servlet中配置    

   ```xml
   <bean id="multipart" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
       <property name="maxUploadSize" value="50000000"/>
       <property name="defaultEncoding" value="UTF-8"/>
   </bean>
   ```

4. java代码

   ```java
   @RequestMapping("/user/upload")
   public String uploadFile(@RequestParam String name,@RequestParam("file")MultipartFile file) throws Exception
       {
           if(!file.isEmpty()){
              file.transferTo(new File("C:\\Users\\geoge\\Desktop\\"+file.getOriginalFilename()));
              return name+"success";
           }
           else
               return "failure";
       }
   ```
   
5. 前端表单

   ```html
   <form method="post" enctype="multipart/form-data">
       <input type="text" name="name"/>
       <input type="file" name="profilePicture"/>
       <input type="submit"/>
   </form>
   ```
   

