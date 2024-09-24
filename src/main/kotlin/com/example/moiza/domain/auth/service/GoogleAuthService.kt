package com.example.moiza.domain.auth.service

import com.example.moiza.domain.auth.exception.NotSchoolUserException
import com.example.moiza.domain.auth.presentation.dto.res.TokenResponse
import com.example.moiza.domain.user.domain.User
import com.example.moiza.domain.user.domain.repository.UserRepository
import com.example.moiza.domain.user.domain.type.Authority
import com.example.moiza.global.feign.GoogleInformationClient
import com.example.moiza.global.feign.auth.dto.res.GoogleInformationResponse
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
            val authority = decideAuthority(email)
            userRepository.save(User(
                email,res.name, res.picture, authority
            ))
        }

        return TokenResponse(
                jwtTokenProvider.createAccessToken(email),
                jwtTokenProvider.createRefreshToken(email)
        )
    }

    private fun decideAuthority(email: String): Authority {
        return when {
            email.endsWith("@bssm.hs.kr") -> Authority.BSSM
            email.endsWith("@dgsw.hs.kr") -> Authority.DGSM
            email.endsWith("@gsm.hs.kr") -> Authority.GSM
            email.endsWith("@dsm.hs.kr") -> Authority.DSM
            else -> throw NotSchoolUserException
        }
    }
}