package model.entities;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Student {

    private Long id;
    private String fullName;
    private Gender gender;
    private LocalDate dateOfBirth;
    private LocalDateTime createdAt;

    public Student(String fullName, Gender gender, LocalDate dateOfBirth) {
        this.id = new Random().nextLong(999);
        this.fullName = fullName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.createdAt = LocalDateTime.now();
    }

    public enum Gender {
        MALE, FEMALE
    }
}
