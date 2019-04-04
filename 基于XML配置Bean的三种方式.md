## 基于XML配置Bean的三种方式

**DI(depedence insert)是IoC(Inverse of Control)的一个更形象化的名字**

## 三种DI方式

1. 接口注入
2. 属性注入
3. 通过构造器注入

### 1. 接口注入(略)

### 2. 属性注入的坑

*Java语言规定，如果类中没有定义任何构造函数，JVM会自动生成一个默认的构造函数；如果定义了，JVM就不会为其自动生成一个默认的构造函数。*

所以如果在某个Bean类中显示地定义了一个带有参数的构造函数，如`public Car(String brand);`，则需要同时提供一个默认的无参数构造函数，否则使用属性注入会抛出异常。

*Spring不会检查Bean中是否有对应的Setter方法，至于方法中是否真正设置了属性，是不做检查的。*

* JavaBean关于属性命名的规范: xxx的属性对应于setXxx()方法

```java
package com.smart.ditype;

public class Car {
    private int maxSpeed;
    public String brand;
    private double price;
    //省略setXXX()方法
}

```

```xml
<!--注册bean-->
<bean id="car" class="com.smart.ditype.Car">
    <property name="brand" value="长城"/>
    <property name="maxSpeed" value="100"/>
    <property name="price" value="100000"/>
</bean>
```

### 3. 构造器注入

通过构造函数将属性的值注入进入

```java
package com.smart.ditype;

public class Car {
    private String corp;
    private String brand;
    private double price;

    public Car(String corp, String brand, double price) {
        this.corp = corp;
        this.brand = brand;
        this.price = price;
    }
    public String toString(){
        return "corp="+corp+";brand="+brand+";price="+price;
    }
}
```

配置文件

```xml
<!--注册bean-->
<bean id="car1" class="com.smart.ditype.Car">
    <constructor-arg type="java.lang.String" value="一汽" index="0"/>
    <constructor-arg type="java.lang.String" value="红旗" index="1"/>
    <constructor-arg type="double" value="10000" index="2"/>
</bean>
```

通过*index*和*type*属性来匹配构造器的参数与类型

* Spring对于构造函数配置的Bean进行实例化有个前提，就是Bean构造函数入参引用对象必须已经准备就绪，避免循环引用，引发死锁。解决该问题的方案是用属性注入。

### 4. 工厂方式注入

略(通过自定义一个Bean，有一个静态或者动态的方法返回一个对象)

## 2.  Bean的作用域

1. singleton,Bean以单例方式存在
2. prototype，每次从容器中调用Bean，都返回一个新的实例
3. request，每次Http请求都会创建一个的Bean
4. session，同一个session共享一个Bean
5. globalSession，同一个全局Session共享一个Bean