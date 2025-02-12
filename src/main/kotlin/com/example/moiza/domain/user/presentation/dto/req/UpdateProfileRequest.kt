package com.example.moiza.domain.user.presentation.dto.req

import com.example.moiza.domain.user.domain.type.EducationStatus
import com.example.moiza.domain.user.domain.type.Job
import com.example.moiza.domain.user.domain.type.Major
import com.example.moiza.domain.user.domain.type.School
import org.jetbrains.annotations.NotNull
import java.time.LocalDate

data class UpdateProfileRequest(
    @NotNull
    val nickname: String,

    @NotNull
    val school: School,

    @NotNull
    val major: Major,

    @NotNull
    val educationStatus: EducationStatus,

    @NotNull
    val enrollmentStartDate: LocalDate,

    @NotNull
    val enrollmentEndDate: LocalDate,

    @NotNull
    val job: Job,

    @NotNull
    val company: String,

    @NotNull
    val introduce: String,
)
