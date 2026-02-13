package mapper;


import model.dto.StudentRequestDto;
import model.dto.StudentResponseDto;
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

    public Student toEntity(StudentRequestDto dto) {
        Student student = new Student();
        student.setFullName(dto.getFullName());
        student.setId(dto.getAge());
        student.setMajor(dto.getMajor());
        return student;
    }
}
