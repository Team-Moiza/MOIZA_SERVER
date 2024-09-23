package com.example.moiza.domain.auth.service

import com.example.moiza.domain.auth.exception.NotSchoolUserException
import com.example.moiza.domain.auth.persentation.dto.res.TokenResponse
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
        val response: GoogleInformationResponse = googleInformationClient
                .getInformation(accessToken)
        val email: String = response.email

        userRepository.findByEmail(email)
                ?: save(email, response.name, response.picture)

        return TokenResponse(
                jwtTokenProvider.createAccessToken(email),
                jwtTokenProvider.createRefreshToken(email)
        )
    }

    private fun save(email: String, nickname: String, profile: String) {
        val authority: Authority

        if (email.endsWith("@bssm.hs.kr"))
            authority = Authority.BSSM
        else if (email.endsWith("@dgsw.hs.kr"))
            authority = Authority.DGSM
        else if (email.endsWith("@gsm.hs.kr"))
            authority = Authority.GSM
        else if (email.endsWith("@dsm.hs.kr"))
            authority = Authority.DSM
        else
            throw NotSchoolUserException

        userRepository.save(User(
            email,
            nickname,
            profile,
            authority,
        ))
    }
}