package com.example.moiza.domain.user.presentation.dto.res

import com.example.moiza.domain.user.domain.type.School

data class InfoUserResponse(
    val nickname: String,
    val profile: String,
    val school: School?,
)

data class UserResponse(
    val nickname: String,
    val profile: String
)
