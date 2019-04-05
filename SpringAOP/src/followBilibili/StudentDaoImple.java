package followBilibili;

import org.springframework.stereotype.Repository;

@Repository("studentDaoId")
public class StudentDaoImple implements StudentDao{
    public void save(){
        System.out.println("dao");
    }
}
