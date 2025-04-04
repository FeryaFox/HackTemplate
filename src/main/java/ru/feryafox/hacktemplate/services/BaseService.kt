package ru.feryafox.hacktemplate.services

import org.springframework.stereotype.Service
import ru.feryafox.hacktemplate.entities.User
import ru.feryafox.hacktemplate.repositories.UserRepository

//@Service
//class BaseService(private val userRepository: UserRepository) {
//    fun getUserOrElseThrow(
//        userId: Long
//    ): User =
//        userRepository.findById(userId)
//            .orElseThrow(
//
//            )
//
//}