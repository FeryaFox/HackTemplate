package ru.feryafox.hacktemplate.models.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на обновление профиля покупателя")
public class UpdateBuyerProfile {

    @NotBlank(message = "Адрес обязателен")
    @Schema(description = "Адрес покупателя", example = "ул. Ленина, д. 10, кв. 5")
    private String address;

    @Past(message = "Дата рождения должна быть в прошлом")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Schema(description = "Дата рождения покупателя", example = "15-06-1990")
    private LocalDate dateOfBirth;
}
