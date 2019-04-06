## Spring事务管理

*默认情况下DAO每一个方法都是一个独立的事务*

### 1. 基于AOP/tx XML的配置
[源码](https://github.com/georgezhou314/Spring/tree/master/spring_transaction)
#### 命名空间的声明

*需要增加tx的命名空间，IDEA自动导入的命名空间有误．．．*

```xml
<beans xmlns:tx="http://www.springframework.org/schema/tx"
xsi:schemaLocation="http://www.springframework.org/schema/tx
http://www.springframework.org/schema/tx/spring-tx.xsd">
</beans>
```

```xml
<!--配置事务管理器，启用事务时必须要有事务管理器-->
<bean id="transactionManager"
      class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="datasource"/>
</bean>
<!--事务增强-->
<tx:advice id="txAdvice" transaction-manager="transactionManager">
    <tx:attributes>
        <tx:method name="transfer" /><!--执行所有advisor中的transfer方法启用事务通知-->
    </tx:attributes>
</tx:advice>
<!--配置切入点与切面-->
<aop:config>
    <aop:pointcut id="mycut" expression="execution(* com.sonydafa.service.impl.AccountServiceImpl.transfer(..))"/>
    <!--aop:advisor是Spring内置的切面，aspect是自己编写的-->
    <aop:advisor advice-ref="txAdvice" pointcut-ref="mycut"/>
</aop:config>
```

*当配置了tx:method时，默认的当运行时触发异常全部回滚。*

### 2. 基于注解配置的事务管理

*applicationContext.xml配置*
[源码](https://github.com/georgezhou314/Spring/tree/master/spring_transaction%20_annotation)

1. 配置**事务管理器**和**启动事务注解驱动**

```xml
<!--事务管理器-->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="datasource"/>
</bean>
<!--配置事务注解驱动-->
<tx:annotation-driven transaction-manager="transactionManager"/>
```

2. 在java文件中需要用事务管理的方法或者类直接加上**@Transactional**即可，在类中加上，整个类都有效，方法即配置注解的方法有效。
3. 无需配置AOP