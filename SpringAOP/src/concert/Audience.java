package concert;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
/*切面类*/
@Aspect
@Component("audience")
public class Audience {
    /*声明一个切点，后续声明直接可以用该函数，不用重复的写一大堆表达式了*/
    @Pointcut("execution(* concert.MusicPerformance.perform(..))")
    public void performance(){
    }
    /*配置环绕通知*/
    @Around("performance()")
    public void watchPerformance(ProceedingJoinPoint jp){
        try{
            System.out.println("Slincecing cell phones");
            System.out.println("Taking Seats");
            jp.proceed();
            System.out.println("CLAP CLAP CLAP");
        }catch (Throwable e){
            System.out.println("Demanding a Refund");
        }
    }
}
