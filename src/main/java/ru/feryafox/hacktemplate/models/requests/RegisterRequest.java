package ru.feryafox.hacktemplate.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Запрос на регистрацию пользователя")
public class RegisterRequest {

    @NotBlank(message = "Логин")
    @Schema(description = "Логин пользователя", example = "Foo")
    private String login;

    @NotBlank(message = "Пароль обязателен")
    @Size(min = 8, max = 32, message = "Пароль должен содержать от 8 до 32 символов")
    @Schema(description = "Пароль пользователя", example = "P@ssw0rd123")
    private String password;

    @NotBlank(message = "Имя обязательно")
    @Size(min = 2, max = 50, message = "Имя должно содержать от 2 до 50 символов")
    @Schema(description = "Имя пользователя", example = "Иван")
    private String firstName;

    @NotBlank(message = "Фамилия обязательна")
    @Size(min = 2, max = 50, message = "Фамилия должна содержать от 2 до 50 символов")
    @Schema(description = "Фамилия пользователя", example = "Петров")
    private String surname;

    @Size(max = 50, message = "Отчество не должно превышать 50 символов")
    @Schema(description = "Отчество пользователя", example = "Сергеевич")
    private String middleName;
}
