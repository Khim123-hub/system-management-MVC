package model.dao;

import model.entities.Student;

import java.util.List;

public interface StudentDao {

    Student save(Student student);

    List<Student> getAll();

    Student getById(Long id);

    boolean removeById(Long id);

    Student update(Long id, Student student);





}
