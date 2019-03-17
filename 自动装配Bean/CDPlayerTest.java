package soundsystem;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//设置运行环境是Spring的运行环境
@RunWith(SpringJUnit4ClassRunner.class)
//装载上下文配置,配置的是CDPlayerConfig类
@ContextConfiguration(classes=CDPlayerConfig.class)
public class CDPlayerTest {
    @Autowired
    private CompactDisc cd;
    @Autowired
    private MediaPlayer player;
    @Test
    public void cdShouldNotBeNull(){
        assertNotNull(cd);
    }
    @Test
    public void play(){
        player.play();
    }
}
