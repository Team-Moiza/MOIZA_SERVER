package com.example.moiza.domain.auth.service

import com.example.moiza.domain.auth.domain.repository.RefreshTokenRepository
import org.springframework.stereotype.Service

@Service
class LogoutService(
        private val refreshTokenRepository: RefreshTokenRepository
) {
    fun execute(refreshToken: String)
        = refreshTokenRepository.deleteById(refreshToken)
}