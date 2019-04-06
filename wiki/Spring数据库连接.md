## Spring数据库连接

### 数据源

1. DBCP数据源

   *依赖于Jakarta commons-pool对象池机制的数据库连接池*

2. C3P0数据源

3. Spring自带的数据源实现

   *这个数据源没有提供池连接的机制。比较适合单元测试和简单的独立应用中使用。*

### 使用数据源Bean的时候可以用一个properties文件存放具体的属性值

*jdbc.properties*

```properties
jdbc.driverClassName=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/sampledb?useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT%2B8&useSSL=false
jdbc.username=root
jdbc.password=******
```

*contextApplication.xml*

```xml
<!--引用外部properties资源-->
<context:property-placeholder location="classpath:jdbc.properties"/>
<!--属性值全部用${}表示-->
<bean id="datasource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
    <property name="driverClassName" value="${jdbc.driverClassName}"/>
    <property name="url" value="${jdbc.url}"/>
    <property name="username" value="${jdbc.username}"/>
    <property name="password" value="${jdbc.password}"/>
</bean>
```

