## AOP参考来自于Spring实战

## AspectJ

*使用AspectJ需要用一个包,`aspectjweaver`,IDEA搭建的Spring项目中默认没有这个包*

*在使用AspectJ用做AOP时，必须在xml中配置`  <aop:aspectj-autoproxy/>`,在测试时被AOP的类必须声明为接口类，在使用接口类才能用JDK动态代理。*

### 实例参考

* <a href="https://github.com/georgezhou314/Spring/tree/master/SpringAOP/src/AopPassArgs">src下的concert是基于注解配置的AOP</a>
* [baseXmlAOP是基于xml配置的AOP](<https://github.com/georgezhou314/Spring/tree/master/SpringAOP/src/baseXmlAop>)
* [AopPassArgs 是被增强的方法有传入参数的情况使用xml配置的AOP](<https://github.com/georgezhou314/Spring/tree/master/SpringAOP/src/AopPassArgs>)

