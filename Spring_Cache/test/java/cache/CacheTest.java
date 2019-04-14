
package cache;
import com.sonydafa.cache.User;
import com.sonydafa.cache.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml","classpath*:cacheContext.xml"})
public class CacheTest {

    @Autowired
    UserService userService;
    @Test
    public void testCache(){
        User user=userService.getUserById("12345");
        System.out.println("age is:"+user.getAge());
        user=userService.getUserById("12346");
        System.out.println("age is:"+user.getAge());
        user=userService.getUserById("12347");

        /*
        userService.removeUser("12344");
        userService.getUserById("12344");
        userService.getUserById("2345");
        userService.getUserById(" 2345");
        userService.getUserById("2345");
        userService.getUserById(" 12344");
        */
    }

}
