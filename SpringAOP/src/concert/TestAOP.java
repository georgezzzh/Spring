package concert;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAOP {
    @Test
    public void test01(){
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("concert/aop.xml");
        Performance performance=(Performance)context.getBean("musicPerformance");
        performance.perform();
    }
}
