## SpringMVC 数据验证与格式化

  1. <a href="#dataValid">数据验证</a>
  2. <a href="#dataFormat">数据格式化</a>


<h3 id="dataValid">Spring数据验证</h3>

#### 1. 预备工作

1. 数据验证依赖的jar包

   javax.validation是一个JSR-303标准定义，hibernate-validator是标准的一个实现包

```xml
  <!-- https://mvnrepository.com/artifact/javax.validation/validation-api -->
    <dependency>
      <groupId>javax.validation</groupId>
      <artifactId>validation-api</artifactId>
      <version>2.0.1.Final</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-validator -->
    <dependency>
      <groupId>org.hibernate</groupId>
      <artifactId>hibernate-validator</artifactId>
      <version>6.0.15.Final</version>
    </dependency>
```
2. xml不需要配置

#### 2. java实例

```java
public class User {
    //数据验证注解
    @NotNull
    @Size(min=1,max=100)
    private String userName;
    private String password;
    private String realName;
    private long salary;
}
```

```java
//错误输出信息时ISO编码，需要转换为UTF-8编码
@RequestMapping(value = "/valid",produces = "application/json;charset=UTF-8")
//前一个验证的结果保存在BindingResult中，成对出现
//不做错误处理，当验证不通过时，直接报异常了
public  String dataValid(@Valid User user, BindingResult bindingResult){
    if(bindingResult.hasErrors()){
        String info="";
        info+="count="+bindingResult.getErrorCount()+"\n";
        List<ObjectError> errors=bindingResult.getAllErrors();
        for(ObjectError tmp:errors){
            //FieldError是ObjectError的子类
            FieldError fr=(FieldError)tmp;
            info+=fr.getField()+","+fr.getDefaultMessage()+"\n";
        }
        return info;
    }
    else
        return user.toString();
}
```

<h3 id="dataFormat">Spring格式化</h3>

```java
public class User {
    private String userName;
    private String password;
    private String realName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    //将1,231.00的字符串转换为long类型
    @NumberFormat(pattern = "#,###.##")
    private long salary;
   }
```

格式化要求输入的形式必须符合pattern对应的，否则会报错。