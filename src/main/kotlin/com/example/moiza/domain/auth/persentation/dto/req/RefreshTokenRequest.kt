package com.example.moiza.domain.auth.persentation.dto.req

import jakarta.validation.constraints.NotNull

data class RefreshTokenRequest(
        @NotNull
        val refreshToken: String
)