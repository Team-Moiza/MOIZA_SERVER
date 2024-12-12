package com.example.moiza.domain.user.facade

import com.example.moiza.domain.user.domain.User
import com.example.moiza.domain.user.domain.repository.UserRepository
import com.example.moiza.domain.user.exception.UserNotFoundException
import org.springframework.security.authentication.AnonymousAuthenticationToken
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

    fun isLogin(): Boolean {
        val authentication = SecurityContextHolder.getContext().authentication

        return authentication != null
                && authentication.isAuthenticated
                && authentication !is AnonymousAuthenticationToken
    }
}