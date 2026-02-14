package model.dto;

import java.time.LocalDate;

public record StudentUpdateDto (
    String fullName,
    String gender,
    LocalDate dateOfBirth
        )  {
}
