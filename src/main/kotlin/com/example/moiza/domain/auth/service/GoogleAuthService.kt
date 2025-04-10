package com.example.moiza.domain.auth.service

import com.example.moiza.domain.auth.presentation.dto.res.TokenResponse
import com.example.moiza.domain.user.domain.User
import com.example.moiza.domain.user.domain.repository.UserRepository
import com.example.moiza.global.feign.google.GoogleInformationClient
import com.example.moiza.global.feign.google.dto.res.GoogleInformationResponse
import com.example.moiza.global.security.jwt.JwtTokenProvider
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GoogleAuthService(
        private val userRepository: UserRepository,
        private val jwtTokenProvider: JwtTokenProvider,
        private val googleInformationClient: GoogleInformationClient
) {
    @Transactional
    fun execute(accessToken: String): TokenResponse {
        val res: GoogleInformationResponse = googleInformationClient
                .getInformation(accessToken)
        val email = res.email

        userRepository.findByEmail(email) ?: run {
            userRepository.save(User(
                email, res.name, BASIC_PROFILE_IMG
            ))
        }

        return TokenResponse(
                jwtTokenProvider.createAccessToken(email),
                jwtTokenProvider.createRefreshToken(email)
        )
    }

    companion object {
        private const val BASIC_PROFILE_IMG = "https://i.pinimg.com/736x/04/15/e3/0415e3a6c56fc6e8f1e0ac1bed4b6aaf.jpg"
    }
}