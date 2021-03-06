package soundsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("cdPlayer")
public class CDPlayer implements MediaPlayer{
    @Autowired
    @Qualifier("blankDisc")
    private CompactDisc compactDisc;
    public void play(){
        compactDisc.play();
    }
}
