package ru.feryafox.hacktemplate.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Запрос на авторизацию пользователя")
public class LoginRequest {

    @NotBlank(message = "Логин")
    @Schema(description = "Логин пользователя", example = "Foo")
    private String login;

    @NotBlank(message = "Пароль обязателен")
    @Size(min = 8, max = 32, message = "Пароль должен содержать от 8 до 32 символов")
    @Schema(description = "Пароль пользователя", example = "P@ssw0rd123")
    private String password;
}
