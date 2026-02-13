package model.service;

import model.dto.StudentRequestDto;
import model.dto.StudentResponseDto;

import java.util.List;

public interface StudentService {
    StudentResponseDto createStudent(StudentRequestDto requestDto);
    StudentResponseDto findById(int id);
    StudentResponseDto update(int id, StudentRequestDto dto);

    List<StudentResponseDto> getAllStudents( int offset, int limit);
    boolean deleteById(Long id);

}
