package ru.feryafox.hacktemplate.models.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.feryafox.hacktemplate.entities.User;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Ответ со всеми пользователями с id")
public class UserWithIdResponce {

    private UUID Id;

    private String firstName;

    private String middleName;

    private String surname;

    public static UserWithIdResponce convertToUserWithIdResponse(User user) {
        if (user == null) {
            return null;
        }

        UserWithIdResponce response = new UserWithIdResponce();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setMiddleName(user.getMiddleName());
        response.setSurname(user.getSurname());
        return response;
    }

    public static List<UserWithIdResponce> convertToUserWithIdResponseList(List<User> users) {
        if (users == null) {
            return null;
        }

        return users.stream()
                .map(UserWithIdResponce::convertToUserWithIdResponse)
                .toList();
    }
}
