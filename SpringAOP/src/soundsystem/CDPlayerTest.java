package soundsystem;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class CDPlayerTest {

    @Test
    public void test(){
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("soundsystem/beans1.xml");
        CompactDisc disc=(CompactDisc) context.getBean("blankDisc");
        TrackCounter counter=(TrackCounter)context.getBean("trackCounter");
        disc.playTrack(1);
        disc.playTrack(3);
        disc.playTrack(5);
        disc.playTrack(1);

    }
}
