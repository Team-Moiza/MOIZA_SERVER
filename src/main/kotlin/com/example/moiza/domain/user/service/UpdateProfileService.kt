package com.example.moiza.domain.user.service

import com.example.moiza.domain.user.facade.UserFacade
import com.example.moiza.domain.user.presentation.dto.req.UpdateProfileRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UpdateProfileService(
    private val userFacade: UserFacade,
) {

    @Transactional
    fun execute(request: UpdateProfileRequest) {
        val user = userFacade.getCurrentUser()
        user.update(request.nickname, request.school, request.major,
            request.educationStatus, request.enrollmentStartDate,
            request.enrollmentEndDate, request.job!!, request.company!!,
            request.introduce)
    }
}
