package spittr;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component("spittleRepository1")
public class SpittleRepositoryImple implements SpittleRepository{
    @Override
    public List<Spittle> findSpittles(long max, int count) {
        ArrayList<Spittle> result=new ArrayList<>();
        result.add(new Spittle("helloJava", LocalDate.now()));
        result.add(new Spittle("testJust",LocalDate.now()));
        return result;
    }
}
