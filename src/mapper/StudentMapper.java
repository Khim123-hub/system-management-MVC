package mapper;


import model.dto.StudentRequestDto;
import model.dto.StudentResponseDto;
import model.dto.StudentUpdateDto;
import model.entities.Student;

public class StudentMapper {

    public Student fromStudentRequestDto(StudentRequestDto requestDto) {
        return new Student(
                requestDto.fullName(),
                Student.Gender.valueOf(requestDto.gender().toUpperCase()),
                requestDto.dateOfBirth()
        );

    }

    public StudentResponseDto toStudentResponse(Student student) {
        return StudentResponseDto.builder()
                .id(student.getId())
                .fullName(student.getFullName())
                .gender(student.getGender().toString())
                .dateOfBirth(student.getDateOfBirth())
                .build();
    }


    public Student fromStudentUpdateDto(StudentUpdateDto updateDto) {
        return new Student(
                updateDto.fullName(),
                Student.Gender.valueOf(updateDto.gender().toUpperCase()),
                updateDto.dateOfBirth()
        );
    }


}
