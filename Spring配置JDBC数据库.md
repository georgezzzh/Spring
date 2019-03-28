## Spring配置JDBC数据库

### 在context.xml文件中配置数据源，注册为Bean

```xml
<!--数据库扫描的类包-->
<context:component-scan base-package="com.smart.dao"/>
<!--数据源-->
<bean id="dataSource1" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
    <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://localhost:3306/sampledb?useUnicode=true&amp;
                characterEncoding=UTF8&amp;serverTimezone=GMT%2B8&amp;useSSL=false"/>
    <property name="username" value="root"/>
    <property name="password" value="---------"/>
</bean>
<!--JDBC模板Bean-->
<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
    <property name="dataSource" ref="dataSource1"/>
</bean>
```

### 在DAO.java中用数据库模板语言

```java
@Repository
public class LoginDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertLoginLog(LoginLog loginLog){
        String sql="insert into t_login_log(user_id,ip,login_datetime) "
                +"values(?,?,?)";
        Object[]args={loginLog.getUserId(),loginLog.getIp(),loginLog.getLoginDate()};
        jdbcTemplate.update(sql,args);
    }
}
```