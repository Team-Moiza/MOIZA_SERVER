package com.example.moiza.domain.user.service

import com.example.moiza.domain.user.facade.UserFacade
import com.example.moiza.domain.user.presentation.dto.res.InfoUserResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProfileService(
    private val userFacade: UserFacade,
) {
    @Transactional(readOnly = true)
    fun execute(): InfoUserResponse {
        val user = userFacade.getCurrentUser()
        return InfoUserResponse(user.nickname, user.profile, user.school)
    }
}