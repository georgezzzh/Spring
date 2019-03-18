package soundsystem; 
import org.springframework.stereotype.Component;

@Component

public class SgtPeppers implements CompactDisc {
    private String title="Sgt. Pepper's Lonely Hearts Club Band";
    private String artists="The Beatles";
    public void play(){
        System.out.println("Hello");
    }
    public CompactDisc SgtPeppers(){
        return new SgtPeppers();
    }
}
