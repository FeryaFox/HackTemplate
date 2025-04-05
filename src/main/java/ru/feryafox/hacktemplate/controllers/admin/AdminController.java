package ru.feryafox.hacktemplate.controllers.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.feryafox.hacktemplate.models.requests.RegisterRequestDelivery;
import ru.feryafox.hacktemplate.models.requests.admin.UpdateUserRequest;
import ru.feryafox.hacktemplate.models.responses.admin.GetAllUsersResponce;
import ru.feryafox.hacktemplate.models.requests.admin.UpdatePasswordRequest;
import ru.feryafox.hacktemplate.models.requests.admin.UpdateRoleRequest;
import ru.feryafox.hacktemplate.services.AdminService;
import ru.feryafox.hacktemplate.services.AuthService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin/")
@RequiredArgsConstructor
public class AdminController {

    public final AdminService adminService;

    @GetMapping("")
    public List<GetAllUsersResponce> getAllUsers() {
        return adminService.getAllUsers();
    }

    @PostMapping("update")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        adminService.updateUser(updateUserRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        adminService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("users/{userId}/update_password")
    public ResponseEntity<?> updatePassword(
            @PathVariable String userId,
            @RequestBody UpdatePasswordRequest updatePasswordRequest
    ) {
        adminService.setPassword(userId, updatePasswordRequest.getNewPassword());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("users/{userId}/update_role")
    public ResponseEntity<?> updateRole(
            @PathVariable String userId,
            @RequestBody UpdateRoleRequest updateRoleRequest
    ) {
        adminService.setRole(userId, updateRoleRequest.getRole());
        return ResponseEntity.noContent().build();
    }
}
