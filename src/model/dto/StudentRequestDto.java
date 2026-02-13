package model.dto;

import java.time.LocalDate;

public record StudentRequestDto(
        String fullName,
        String gender,
        LocalDate dateOfBirth
) {

}
