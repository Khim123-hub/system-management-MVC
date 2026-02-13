package model.service;

import exception.StudentException;
import mapper.StudentMapper;
import model.dao.StudentDao;
import model.dto.StudentRequestDto;
import model.dto.StudentResponseDto;
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

        if (requestDto.dateOfBirth().isAfter(LocalDate.now().minusYears(4))){
            throw new StudentException("Student hasn't born yet");
        }

        Student student = mapper.fromStudentRequestDto(requestDto);
        Student saveStudent = dao.save(student);

        return mapper.toStudentResponse(saveStudent);
    }

    @Override
    public StudentResponseDto findById(int id) {
        Student student = dao.findById(id);
        if (student == null) return null;
        return mapper.toStudentResponse(student);

    }

    @Override
    public StudentResponseDto update(int id, StudentRequestDto dto) {
        Student updatedData = mapper.toEntity(dto);
        Student result = dao.update(id, updatedData);
        if (result == null) return null;
        return mapper.toResponseDto(result);
    }


    @Override
    public List<StudentResponseDto> getAllStudents(int offset, int limit) {
        return dao.getAll().stream()
                .map(mapper::toStudentResponse)
                .toList();
    }

    @Override
    public boolean deleteById(Long id) {

        return dao.deleteById(id);
    }
}
