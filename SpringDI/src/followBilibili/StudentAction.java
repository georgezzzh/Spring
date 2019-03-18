package followBilibili;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//比component更进一层
@Controller("StudentActionId")
public class StudentAction {
    //自动装配
    @Autowired
    private StudentService studentService;
    public void execute(){
        System.out.println("action");
        studentService.addStudent();
    }
}
