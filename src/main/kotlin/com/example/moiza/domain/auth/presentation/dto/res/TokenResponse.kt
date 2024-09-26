package com.example.moiza.domain.auth.presentation.dto.res

data class TokenResponse(
        val accessToken: String,
        val refreshToken: String
)