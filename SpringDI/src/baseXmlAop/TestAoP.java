package baseXmlAop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAoP {
    @Test
    public void test(){
        ApplicationContext context=new ClassPathXmlApplicationContext("baseXmlAop/spring.xml");
        Performance performance=(Performance) context.getBean("performance");
        performance.perform();
    }
}
