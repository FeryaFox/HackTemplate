package ru.feryafox.hacktemplate.controllers.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.feryafox.hacktemplate.models.requests.RegisterRequestDelivery;
import ru.feryafox.hacktemplate.services.AdminService;
import ru.feryafox.hacktemplate.services.AuthService;

@RestController
@RequestMapping("/admin/")
@RequiredArgsConstructor
public class AdminController {

    public final AdminService adminService;


}
