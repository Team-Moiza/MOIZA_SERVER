package com.example.moiza.domain.portfolio.presentation.dto.res

import com.example.moiza.domain.code.domain.Code
import com.example.moiza.domain.code.presentation.dto.CodeResponse
import com.example.moiza.domain.portfolio.presentation.dto.req.*
import com.example.moiza.domain.user.domain.type.EducationStatus
import com.example.moiza.domain.user.domain.type.Job
import com.example.moiza.domain.user.domain.type.Major
import com.example.moiza.domain.user.domain.type.School
import java.time.LocalDateTime

data class PortfolioListResponse(
    val id: Long,
    val name: String,
    val school: School?,
    val company: String?,
    val major: Major?,
    val profile: String,
    val introduce: String?,
    val likeCnt: Long,
    val codes: List<Code>?
)

data class PortfolioDetailResponse(
    val id: Long,
    val name: String,
    val job: Job?,
    val school: School?,
    val introduce: String?,
    val company: String?,
    val educationStatus: EducationStatus?,
    val major: Major?,
    val introduction: IntroductionDto?,
    val projects: List<ProjectDto>?,
    val qualifications: List<QualificationDto>?,
    val awards: List<AwardDto>?,
    val links: List<LinkDto>?,
    val codes: List<CodeResponse>?,
    val likeCnt: Long,
)

data class MyPortfolioResponse(
    val id: Long,
    val title: String,
    val updatedAt: LocalDateTime,
    val isPublished: Boolean,
    val likeCnt: Long,
)
