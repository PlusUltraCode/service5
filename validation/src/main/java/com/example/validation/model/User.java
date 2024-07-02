package com.example.validation.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(value= PropertyNamingStrategies.SnakeCaseStrategy.class)
public class User {

    @NotBlank
    private String name;

    @NotBlank
    @Size(min=1,max=12)
    private String password;

    @Min(1)
    @Max(100)
    private Integer age;

    @Email
    private String email;

    @NotBlank
    private String phoneNumber;

    @FutureOrPresent
    private LocalDateTime registerAt;
}
