package com.example.moiza.domain.user.facade

import com.example.moiza.domain.user.exception.UserNotFoundException
import com.example.moiza.domain.user.domain.User
import com.example.moiza.domain.user.domain.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class UserFacade(
    private val userRepository: UserRepository
) {
    fun getCurrentUser(): User {
        val email: String = SecurityContextHolder.getContext().authentication.name
        return getUserByEmail(email)
    }

    fun getUserByEmail(email: String)
        = userRepository.findByEmail(email) ?: throw UserNotFoundException

    fun isLogin()
        = SecurityContextHolder.getContext().authentication.isAuthenticated
}