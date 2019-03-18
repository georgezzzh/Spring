package soundsystem;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//设置运行环境是Spring的运行环境
@RunWith(SpringJUnit4ClassRunner.class)
//装载上下文配置,配置的是CDPlayerConfig类
@ContextConfiguration(classes= CDPlayerConfig.class)
public class CDPlayerTest {
    //将一个CompactDisc对象注解到当前类中
    @Autowired
    private CompactDisc cd;
    @Test
    public void test0(){
        cd.play();
    }
    /*上面用来测试注解配置*/
    /*
    public void test(){
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("beans1.xml");
        CDPlayer player=(CDPlayer) context.getBean("cdPlayer");
        player.play();
    }
    */
}
