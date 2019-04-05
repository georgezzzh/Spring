package AopPassArgs;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPassArgs{
    @Test
    public void test01(){
        //一开始报异常，应该是直接复制过来的文件，实现的接口类是上一个文件夹的CompactDisc
        //会导致 $Proxy cannot be cast to using aop
        ApplicationContext context=new ClassPathXmlApplicationContext("AopPassArgs/spring.xml");
        CompactDisc disc=(CompactDisc) context.getBean("blankDisc");
        disc.playTrack(1);
        disc.playTrack(3);
        disc.playTrack(1);
    }

}
