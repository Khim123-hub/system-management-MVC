package model.dao;

import db.StudentDb;
import model.entities.Student;

import java.util.List;
import java.util.Optional;

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
        Optional<Student> student = studentDb.getStudentList()
                .stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();

        return student.orElse(null);
    }

    @Override
    public boolean removeById(Long id) {
        return studentDb.getStudentList()
                .removeIf(student -> student.getId().equals(id)
                );

    }

    @Override
    public Student update(Long id, Student student) {
        Optional<Student> existingStudentOpt = studentDb.getStudentList()
                .stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();

        if (existingStudentOpt.isEmpty()) {
            return null;
        }

        Student existingStudent = existingStudentOpt.get();
        existingStudent.setFullName(student.getFullName());
        existingStudent.setGender(student.getGender());
        existingStudent.setDateOfBirth(student.getDateOfBirth());

        return existingStudent;
    }


}
