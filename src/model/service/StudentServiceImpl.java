package model.service;

import exception.StudentException;
import mapper.StudentMapper;
import model.dao.StudentDao;
import model.dto.StudentRequestDto;
import model.dto.StudentResponseDto;
import model.dto.StudentUpdateDto;
import model.entities.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class StudentServiceImpl  implements StudentService{

    private final StudentDao dao;
    private final StudentMapper mapper;

    public StudentServiceImpl(StudentDao dao, StudentMapper mapper){
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public StudentResponseDto createStudent(StudentRequestDto requestDto) {

        List<Student> existingStudents = dao.getAll();
        boolean nameExists = existingStudents.stream()
                .anyMatch(s -> s.getFullName().equalsIgnoreCase(requestDto.fullName()));

        if (nameExists) {
            throw new StudentException("Student with name '" + requestDto.fullName() + "' already exists");
        }

        Student student = mapper.fromStudentRequestDto(requestDto);
        Student savedStudent = dao.save(student);

        return mapper.toStudentResponse(savedStudent);
    }

    @Override
    public List<StudentResponseDto> getAllStudents(int offset, int limit) {
        if (limit <= 0) {
            throw new StudentException("Limit must be greater than 0");
        }
        if (offset < 0) {
            throw new StudentException("Offset must be greater than or equal to 0");
        }

        List<Student> allStudents = dao.getAll();

        return allStudents.stream()
                .skip(offset)
                .limit(limit)
                .map(mapper::toStudentResponse)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(Long id) {
        Student student = dao.getById(id);
        if (student == null) {
            throw new StudentException("Student with ID " + id + " not found");
        }

        return dao.removeById(id);
    }

    @Override
    public StudentResponseDto updateById(Long id, StudentUpdateDto updateDto) {
        Student existingStudent = dao.getById(id);
        if (existingStudent == null) {
            throw new StudentException("Student with ID " + id + " not found");
        }

        List<Student> allStudents = dao.getAll();
        boolean nameConflict = allStudents.stream()
                .anyMatch(s -> !s.getId().equals(id) &&
                        s.getFullName().equalsIgnoreCase(updateDto.fullName()));

        if (nameConflict) {
            throw new StudentException("Another student with name '" + updateDto.fullName() + "' already exists");
        }

        Student studentToUpdate = mapper.fromStudentUpdateDto(updateDto);
        Student updatedStudent = dao.update(id, studentToUpdate);

        return mapper.toStudentResponse(updatedStudent);
    }

}
