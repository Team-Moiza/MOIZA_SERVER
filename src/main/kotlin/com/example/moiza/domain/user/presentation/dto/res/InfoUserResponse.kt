package com.example.moiza.domain.user.presentation.dto.res

import com.example.moiza.domain.user.domain.User
import com.example.moiza.domain.user.domain.type.EducationStatus
import com.example.moiza.domain.user.domain.type.Job
import com.example.moiza.domain.user.domain.type.Major
import com.example.moiza.domain.user.domain.type.School
import java.time.LocalDate

data class InfoUserResponse(
    val nickname: String,
    val profile: String,
    val email: String,
    val school: School?,
    val major: Major?,
    val educationStatus: EducationStatus?,
    val enrollmentStartDate: LocalDate?,
    val enrollmentEndDate: LocalDate?,
    val job: Job?,
    val company: String?,
    val introduce: String?,
    ) {
    constructor(user: User): this(
        nickname = user.nickname,
        profile = user.profile,
        email = user.email,
        school = user.school,
        major = user.major,
        educationStatus = user.educationStatus,
        enrollmentStartDate = user.enrollmentStartDate,
        enrollmentEndDate = user.enrollmentEndDate,
        job = user.job,
        company = user.company,
        introduce = user.introduce,
    )
}