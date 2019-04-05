package baseXmlAop;

import org.aspectj.lang.ProceedingJoinPoint;

public class AroundAdvice {
    public void watchPerformance(ProceedingJoinPoint pj){
        try{
            System.out.println("Watcher-Silencing cell phones");
            System.out.println("Watcher-Taking Seats");
            pj.proceed();
            System.out.println("Watcher-CLAP CLAP CLAP");
        }catch (Throwable e){
            System.out.println("Watcher-Demanding a refund");
        }
    }
}
