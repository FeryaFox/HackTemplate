package ru.feryafox.hacktemplate.models.responses.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.feryafox.hacktemplate.entities.Role;
import ru.feryafox.hacktemplate.entities.User;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Ответ со всеми пользователями")
public class GetAllUsersResponce {

    private String id;

    private String login;

    private String firstName;

    private String middleName;

    private String surname;

    private Role.RoleName role;

    private Date registrationDate;

    public static GetAllUsersResponce fromUser(User user) {
        if (user == null) {
            return null;
        }

        GetAllUsersResponce response = new GetAllUsersResponce();
        response.setId(user.getId().toString());
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
