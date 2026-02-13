package db;
import model.entities.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class StudentDb {

    private final List<Student> studentList;

    public StudentDb() {
        studentList = new ArrayList<>(){{
            add(new Student(
                    "Bidin Messi", Student.Gender.MALE, LocalDate.now().minusYears(21)
            ));
            add(new Student(
                    "Kimlong", Student.Gender.MALE, LocalDate.of(2010, 5,5)
            ));
            add(new Student(
                    "Sovanreach", Student.Gender.FEMALE, LocalDate.now().minusYears(18)
            ));
        }};
    }

    public List<Student> getStudentList() {
        return studentList;
    }


}
