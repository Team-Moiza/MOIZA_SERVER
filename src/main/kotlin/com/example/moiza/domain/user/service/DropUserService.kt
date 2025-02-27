package com.example.moiza.domain.user.service

import com.example.moiza.domain.user.domain.repository.UserRepository
import com.example.moiza.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DropUserService(
    private val userFacade: UserFacade,
    private val userRepository: UserRepository,
) {
    @Transactional
    fun execute() {
        val currentUser = userFacade.getCurrentUser()
        userRepository.delete(currentUser)
    }
}