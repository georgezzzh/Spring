package followBilibili;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestAnnoIoc {
    @Test
    public void demo01(){
        String xml="student.xml";
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext(xml);
        StudentAction studentAction=(StudentAction)applicationContext.getBean("StudentActionId");

        studentAction.execute();
    }
}
