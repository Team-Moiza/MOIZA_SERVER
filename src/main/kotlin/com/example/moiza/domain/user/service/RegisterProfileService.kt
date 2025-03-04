package com.example.moiza.domain.user.service

import com.example.moiza.domain.user.facade.UserFacade
import com.example.moiza.domain.user.presentation.dto.req.RegisterProfileRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RegisterProfileService(
    private val userFacade: UserFacade,
) {

    @Transactional
    fun execute(request: RegisterProfileRequest) {
        val user = userFacade.getCurrentUser()
        user.registerUpdate(request.school, request.major, request.educationStatus)
    }
}
