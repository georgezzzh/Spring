## SpringMVC 文件上传

### 1.  使用apache.common方式上传

1. 依赖包(commons-fileupload, commons-io)两个jar包

2. web.xml中配置

   ```xml
   <multipart-config>
       <max-file-size>2097152</max-file-size>
       <max-request-size>2097152</max-request-size>
   </multipart-config>
   ```

3. dispattcher-servlet中配置    

   ```xml
   <bean id="multipart" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
       <property name="maxUploadSize" value="50000000"/>
       <property name="defaultEncoding" value="UTF-8"/>
   </bean>
   ```

4. java代码

   ```java
   public String processRegistraction(HttpServletRequest request)
   throws IOException
   {
       //创建一个多分解的容器
       CommonsMultipartResolver cmr = new CommonsMultipartResolver(request.getSession().getServletContext());
       //设置编码
       cmr.setDefaultEncoding("utf-8");
       //判断是否有文件上传
       if(cmr.isMultipart(request)){
           //将request转换成多分解请求
           MultipartHttpServletRequest mhs = cmr.resolveMultipart(request);
           //根据input中存在的name来获取是否存在上传文件
           MultipartFile mf = mhs.getFile("profilePicture");
           //创建文件保存名
           File file = new File(mf.getOriginalFilename());
           //保存文件到磁盘中
           mf.transferTo(file);
           System.out.println("current Path:"+file.getAbsolutePath());
       }
       return "profile";
   }
   ```

### 2. 使用Spring自带的StandardServletMultipartResolver解析

1. 依赖包(配置好Spring的依赖包就可以，不需要另外的包)
2. web.xml配置(和commons配置相同)
3. dispatcher-servlet.xml配置       
    ```xml
    <bean class="org.springframework.web.multipart.support.StandardServletMultipartResolver">
    </bean>
    ```
4. Java部分

    ```java
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String process(HttpServletRequest request)
    throws IOException{
        StandardServletMultipartResolver cmr = new StandardServletMultipartResolver();
        if(cmr.isMultipart(request)){
            MultipartHttpServletRequest mhs = cmr.resolveMultipart(request);
            mhs.setCharacterEncoding("utf-8");
            MultipartFile mf = mhs.getFile("profilePicture");
            String root="C:\\Users\\geoge\\IdeaProjects\\Spring_Maven\\src\\main\\webapp\\tmp\\upload\\";
            root+=mf.getOriginalFilename();
            mf.transferTo(new File(root));
        }
        return "profile";
    }
    ```