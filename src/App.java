import controller.StudentController;
import db.StudentDb;
import mapper.StudentMapper;
import model.dao.StudentDao;
import model.dao.StudentDaoImpl;
import model.service.StudentService;
import model.service.StudentServiceImpl;
import view.StudentView;

public class App {
    public static void main(String[] args) {

        StudentDb db = new StudentDb();


        StudentView view = new StudentView();
        StudentDao dao = new StudentDaoImpl(db);
        StudentMapper mapper = new StudentMapper();
        StudentService service = new StudentServiceImpl(dao, mapper);
        StudentController controller = new StudentController(view, service);

        controller.run();


    }
}

