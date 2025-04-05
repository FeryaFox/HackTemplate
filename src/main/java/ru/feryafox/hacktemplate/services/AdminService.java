package ru.feryafox.hacktemplate.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.feryafox.hacktemplate.models.responses.admin.GetAllUsersResponce;
import ru.feryafox.hacktemplate.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {

    UserRepository userRepository;

    public List<GetAllUsersResponce> getAllUsers() {
        return userRepository.findAll().stream()
                .map(GetAllUsersResponce::fromUser)
                .collect(Collectors.toList());
    }

}
