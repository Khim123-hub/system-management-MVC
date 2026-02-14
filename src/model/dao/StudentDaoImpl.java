package model.dao;

import db.StudentDb;
import model.entities.Student;

import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private final StudentDb studentDb;
    public StudentDaoImpl(StudentDb studentDb){

        this.studentDb = studentDb;
    }

    @Override
    public Student save(Student student) {
        studentDb.getStudentList().add(student);
        return student;
    }

    @Override
    public List<Student> getAll() {
        return studentDb.getStudentList();
    }

    @Override
    public Student getById(Long id) {
        return null;
    }

    @Override
    public boolean removeById(Long id) {
        return false;
    }

    @Override
    public Student update(Long id, Student student) {
        return null;
    }


}
