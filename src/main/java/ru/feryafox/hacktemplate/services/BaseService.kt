package ru.feryafox.hacktemplate.services

import org.springframework.stereotype.Service
import ru.feryafox.hacktemplate.entities.User
import ru.feryafox.hacktemplate.exceptions.auth.user.UserIsNotExistException
import ru.feryafox.hacktemplate.repositories.UserRepository
import java.util.UUID

@Service
class BaseService(private val userRepository: UserRepository) {
    fun getUserOrElseThrow(
        userId: UUID
    ): User =
        userRepository.findById(userId)
            .orElseThrow{ UserIsNotExistException(userId.toString()) }
}