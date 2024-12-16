package com.example.moiza.domain.user.presentation.dto.req

import com.example.moiza.domain.user.domain.type.EducationStatus
import com.example.moiza.domain.user.domain.type.Major
import com.example.moiza.domain.user.domain.type.School
import org.jetbrains.annotations.NotNull

data class UpdateProfileRequest(
    @NotNull
    val school: School,

    @NotNull
    val major: Major,

    @NotNull
    val educationStatus: EducationStatus,

    @NotNull
    val introduce: String,
)
