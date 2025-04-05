package ru.feryafox.hacktemplate.models.requests.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.feryafox.hacktemplate.entities.Role;
import ru.feryafox.hacktemplate.entities.User;
import ru.feryafox.hacktemplate.models.responses.admin.GetAllUsersResponce;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@Schema(description = "Запрос на редактирование задачи")
public class UpdateUserRequest {

    private UUID id;

    private String login;

    private String firstName;

    private String middleName;

    private String surname;

    private String role;

    public static GetAllUsersResponce fromUser(User user) {
        if (user == null) {
            return null;
        }

        GetAllUsersResponce response = new GetAllUsersResponce();
        response.setLogin(user.getLogin());
        response.setFirstName(user.getFirstName());
        response.setMiddleName(user.getMiddleName());
        response.setSurname(user.getSurname());
        response.setRegistrationDate(user.getCreateAt());

        if (user.getRoles() != null && !user.getRoles().isEmpty()) {
            Role firstRole = user.getRoles().iterator().next();
            response.setRole(Role.RoleName.valueOf(firstRole.getRoleName()));
        }

        return response;
    }

}
