package com.example.moiza.domain.user.presentation.dto.req

import com.example.moiza.domain.user.domain.type.EducationStatus
import com.example.moiza.domain.user.domain.type.Job
import com.example.moiza.domain.user.domain.type.Major
import com.example.moiza.domain.user.domain.type.School
import org.jetbrains.annotations.NotNull
import java.time.LocalDate

data class RegisterProfileRequest(
    val school: School,
    val major: Major,
    val educationStatus: EducationStatus,
)
