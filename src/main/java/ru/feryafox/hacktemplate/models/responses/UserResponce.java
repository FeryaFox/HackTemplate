package ru.feryafox.hacktemplate.models.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Пользователь, передаваемый в запросах")
public class UserResponce {

    private String name;

    private String middleName;

    private String lastName;
}
