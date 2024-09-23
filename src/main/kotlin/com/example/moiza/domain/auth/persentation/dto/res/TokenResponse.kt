package com.example.moiza.domain.auth.persentation.dto.res

data class TokenResponse(
        val accessToken: String,
        val refreshToken: String
)