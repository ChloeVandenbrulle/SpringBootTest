package org.example.springboottest2.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import org.example.springboottest2.enums.GenderEnum;
import org.example.springboottest2.validators.AdultAge;
import org.example.springboottest2.validators.ValidGender;

import java.time.LocalDate;

public record UserDTO(
        @NotBlank(message = "Username is required")
        String userName,

        @Past(message = "Birthdate shouldn't be in the future")
        @NotNull(message = "Birthdate is required")
        @AdultAge()
        LocalDate birthdate,

        @NotBlank(message = "Country of residence is required")
        @Pattern(regexp = "^\\s*([Ff]rance?|FRANCE|[Ff][Rr]|FR)\\s*$", message = "Only French Residents are accepted")
        String countryOfResidence,

        @Pattern(regexp = "^[+]?[0-9]{10,12}$", message = "Invalid phone number format")
        String phoneNumber,

        @ValidGender()
        GenderEnum gender
) {
}
