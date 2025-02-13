package com.example.moiza.domain.portfolio.presentation.dto.res

import com.example.moiza.domain.code.presentation.dto.CodeResponse
import com.example.moiza.domain.portfolio.presentation.dto.req.*
import com.example.moiza.domain.user.domain.type.Major
import com.example.moiza.domain.user.domain.type.School

data class PortfolioListResponse(
    val id: Long,
    val name: String,
    val school: School,
    val major: Major,
    val profile: String,
    val introduce: String
)

data class PortfolioDetailResponse(
    val id: Long,
    val name: String,
    val major: Major,
    val introduce: String,
    val introduction: IntroductionDto?,
    val projects: List<ProjectDto>?,
    val qualifications: List<QualificationDto>?,
    val awards: List<AwardDto>?,
    val links: List<LinkDto>?,
    val codes: List<CodeResponse>?
)
