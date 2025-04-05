package ru.feryafox.hacktemplate.models.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.feryafox.hacktemplate.entities.User;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Пользователь, передаваемый в запросах")
public class UserResponce {

    private String name;

    private String middleName;

    private String surname;

    public static UserResponce convertToUserResponse(User user) {
        if (user == null) {
            return null;
        }

        UserResponce response = new UserResponce();
        response.setName(user.getFirstName());
        response.setMiddleName(user.getMiddleName());
        response.setSurname(user.getSurname());
        return response;
    }

    public static List<UserResponce> convertToUserResponseList(List<User> users) {
        if (users == null) {
            return null;
        }

        return users.stream()
                .map(UserResponce::convertToUserResponse)
                .toList();
    }
}
