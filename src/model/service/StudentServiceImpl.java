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

public class StudentServiceImpl  implements StudentService{

    private final StudentDao dao;
    private final StudentMapper mapper;

    public StudentServiceImpl(StudentDao dao, StudentMapper mapper){
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public StudentResponseDto createStudent(StudentRequestDto requestDto) {

        if (requestDto.dateOfBirth().isAfter(LocalDate.now().minusYears(4))) {
            throw new StudentException("Student hasn't born yet");
        }
        Student student = mapper.fromStudentRequestDto(requestDto);

        Student savedStudent = dao.save(student);
        return mapper.toStudentResponse(savedStudent);
    }

    @Override
    public List<StudentResponseDto> getAllStudents(int offset, int limit) {
        return dao.getAll().stream()
                .map(mapper::toStudentResponse)
                .toList();
    }

    @Override
    public boolean deleteById(Long id) {
        return dao.removeById(id);
    }

    @Override
    public StudentResponseDto updateById(Long id, StudentUpdateDto updateDto) {
        return null;
    }

}
