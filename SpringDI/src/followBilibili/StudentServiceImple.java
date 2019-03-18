package followBilibili;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImple implements StudentService {
    private StudentDao studentDao;
    //需要注入的时候要声明Autowired
    //Qualifer是按照ID来注入,Autowired是按照类型注入
    @Autowired
    @Qualifier("studentDaoId")
    public void setStudentDao(StudentDao studentDao){
        this.studentDao=studentDao;
    }
    public void addStudent(){
        System.out.println("Service");
        studentDao.save();
    }
}
