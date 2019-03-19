package concert;

import org.springframework.stereotype.Component;

@Component("musicPerformance")
public class MusicPerformance implements Performance {
    public void perform(){
        System.out.println("Now start perform");
    }
}
