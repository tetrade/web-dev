package ru.nicetu.predator.u.modules.launch.chart.mapper;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Validated
public class UserSignInDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String secretToken;
}
