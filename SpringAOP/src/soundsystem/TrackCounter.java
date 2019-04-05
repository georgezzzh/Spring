package soundsystem;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component("trackCounter")
public class TrackCounter {
    private Map<Integer,Integer> trackCounts=new HashMap<>();
    @Pointcut("execution(* soundsystem.CompactDisc.playTrack(int))&&args(trackNumber)")
    public void trackPlayed(int trackNumber){}

    @Before("trackPlayed(trackNumber)")
    public void countTrack(int trackNumber){
        int currentNumber=getPlayCount(trackNumber);
        trackCounts.put(trackNumber,currentNumber);
        System.out.println(trackCounts);
    }
    public int getPlayCount(int trackNumber){
        return trackCounts.containsKey(trackNumber)?trackCounts.get(trackNumber)+1:1;
    }
}
