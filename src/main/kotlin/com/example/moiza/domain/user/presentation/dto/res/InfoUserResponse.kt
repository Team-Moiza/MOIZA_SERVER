package com.example.moiza.domain.user.presentation.dto.res

import com.example.moiza.domain.user.domain.type.Authority

data class InfoUserResponse(
    val nickname: String,
    val profile: String,
    val authority: Authority
)