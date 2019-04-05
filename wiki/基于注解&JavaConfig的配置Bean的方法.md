## 基于注解的配置Bean的方法

*@Component、@Repository、@Service、@Controller*四个用来Bean的注解，分别是一般的Bean,DAO层的Bean，Service层，Controller层的Bean。

### 1. Context中定义自动扫描注解定义的Bean

`<context:component-scan base-package="com.smart.anno"/>`容器会扫描基包下所有的类。

### 2. 使用@Autowired的required属性

如果希望Spring即使找不到匹配的Bean完成注入也不要报错，可以使用`@Autowired(required=false)`进行标注

例如

```java
@Service
public class LoginService{
    @Autowired(required=false)
    private LoginDao loginDao;
}
```

### 3. 使用@Qualifier指定注入Bean名称

实例

```java
@Service
public class LoginService{
    @AutoWired
    private LoginDao loginDao;
    @AutoWired
    //注入名为userDao，类型为userDao的Bean
    @Qualifier("userDao)
    private UserDao userDao;
}
```

### 4.对类方法进行标注

```java
@Component
public class Car {
    private Montor motor;
    @Autowired
    public void setMotor(Montor m){motor=m;}
    public void print(){
        motor.print();
    }
}
```

```java
@Component
public class Montor {
    @Value("SIMENSE")
    private String motor;
    public void print(){
        System.out.println("This motor is:"+motor);
    }
}
```

注解可以用于属性，也可以用于方法，在最初被引用的引用的类，可以用`@Value`注解来设置初始值。

## 基于Java类的配置

* 定义一个conf.java的文件

```java

@Configuration
public class AppConf {
    @Bean
    public Montor getMontor(){
        Montor montor=new Montor();
        montor.setMotor("SIMENSE");
        return montor;
    }
    @Bean
    public Car getCar(){
        Car car=new Car();
        car.setMotor(getMontor());
        return car;
    }
}

```

类注解为@Configuration,方法注解为Bean，这样就定义了配置类了。

* 测试类例子

```java
@ContextConfiguration("classpath*:/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class IoCTest extends AbstractTestNGSpringContextTests{
    @Test
    public void getResource() throws Throwable{
    	//加载配置类
        ApplicationContext ctx=new AnnotationConfigApplicationContext(AppConf.class);
        //获取配置的Bean
        Car car=ctx.getBean(Car.class);
        car.print();
    }
}
```

*AnnotationConfigApplicationContext*还支持通过编码的方式加载多个@Configuration配置类，然后通过刷新容器应用这些配置类，详情参考*精通Spring4.x企业应用开发实战P167*

### 通过代码一个一个地注册配置类，然后通过@import将多个配置类组装在一个配置类中

例如

```java
//注册Montor和Car的Bean  
@Configuration
public class CarConf {
    @Bean
    public Montor getMontor(){
        Montor montor=new Montor();
        montor.setMotor("SIMENSE");
        return montor;
    }
    @Bean
    public Car getCar(){
        Car car=new Car();
        car.setMotor(getMontor());
        return car;
    }
}
```

```java
 //注册Driver的Bean，并命名为driver 
 @Configuration
public class DriverConf {
    @Bean("driver")
    public Driver getDriver(){
        return new Driver();
    }
}
```

```java
@Configuration
@Import({CarConf.class,DriverConf.class})
public class AppConf {

}
```

*通过import将DriverConf和CarConf导入到一起*

```xml
<context:component-scan base-package="com.smart.config" resource-pattern="AppConf.class"/>
```

最后在context.xml中配置启动自动扫描，告诉扫描的config类，通过*resource-pattern*明确最终组装的配置类。

### *通过xml启动Spring容器的方法*

标注了@Configuration的配置类和标注了@Component的类同样也是一个Bean，可以被spring的`<context:component-scan/>`扫描到，仅需在xml中通过component-scan配置响应的配置类即可

### 通过@Configuration配置类引用XML配置信息

详情查看`精通Spring4.x企业开发p169`

