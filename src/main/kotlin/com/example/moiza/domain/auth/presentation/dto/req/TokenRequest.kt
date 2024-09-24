package com.example.moiza.domain.auth.presentation.dto.req

import jakarta.validation.constraints.NotNull

data class TokenRequest(
        @NotNull
        val token: String
)