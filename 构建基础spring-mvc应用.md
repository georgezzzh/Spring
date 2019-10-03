### 构建基础spring-mvc应用

1. 在IDEA中选则新建maven工程(web项目)

2. 建立之后增加framework支持(选择spring-mvc和thymeleaf)

3. 配置tomcat运行

   ![](https://github.com/georgezhou314/imageRepo/raw/master/IDEA/spring-web-init/artifact.png)

4. 注意application context修改为**/**   ,否则项目识别不了xml的配置，会导致启动一直出现404

![](https://github.com/georgezhou314/imageRepo/raw/master/IDEA/spring-web-init/deployment.png)

