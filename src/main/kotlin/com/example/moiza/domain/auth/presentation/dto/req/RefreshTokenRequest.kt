package com.example.moiza.domain.auth.presentation.dto.req

import jakarta.validation.constraints.NotNull

data class RefreshTokenRequest(
        @NotNull
        val refreshToken: String
)