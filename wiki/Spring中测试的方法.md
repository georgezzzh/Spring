## Spring中测试的方法

1. 需要配置的context放在maven工程中的resources目录下即可，Java编译之后xml文件将在classes目录下，是所以当引用的时候只需要写*classpath*:applicationContext.xml即可。

2. 一个简单的Test程序

   ```java
   package com.sonydafa.service.impl;
   
   import com.sonydafa.service.IAccountService;
   import org.junit.Test;
   import org.junit.runner.RunWith;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.test.context.ContextConfiguration;
   import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
   //规定运行环境
   @RunWith(SpringJUnit4ClassRunner.class)
   //定义配置文件
   @ContextConfiguration(locations = "classpath*:applicationContext.xml")
   public class TransferTest {
       //需要测试哪一项，直接注入进来就可以测试了
       @Autowired
       private IAccountService accountService;
       @Test
       public void test01(){
           accountService.transfer("tom","jack",1000);
       }
   }
   ```

这样配置好测试环境就不必用*ApplicationContext*对象来获取Spring中的Bean了。

