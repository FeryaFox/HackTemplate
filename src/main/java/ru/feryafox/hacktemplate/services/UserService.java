package ru.feryafox.hacktemplate.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.feryafox.hacktemplate.entities.User;
import ru.feryafox.hacktemplate.models.responses.UserWithIdResponce;
import ru.feryafox.hacktemplate.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public List<UserWithIdResponce> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserWithIdResponce::convertToUserWithIdResponse)
                .collect(Collectors.toList());
    }
}
