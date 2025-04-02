package ru.feryafox.hacktemplate.controllers.profile;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.feryafox.hacktemplate.models.responses.UserProfileResponse;
import ru.feryafox.hacktemplate.services.InternalService;

@RestController
@RequestMapping("/intern/auth/profile/")
@RequiredArgsConstructor
public class InternProfileController {
    private final InternalService internalService;

    @GetMapping("buyer/{userId}")
    public ResponseEntity<?> getProfileIntern(
            @PathVariable(value = "userId") String usedId
    ) {
        UserProfileResponse response = internalService.getBuyerProfileServiceByUser(usedId);
        return ResponseEntity.ok(response);
    }
}
