package com.example.moiza.domain.portfolio.presentation.dto.req

data class UpdatePortfolioRequest(
    val title: String,
    val introduce: String,
    val projects: List<ProjectDto>? = null,
    val awards: List<AwardDto>? = null,
    val qualifications: List<QualificationDto>? = null,
    val links: List<LinkDto>? = null,
    val introduction: IntroductionDto? = null,
    val codes: List<CodeDto>? = null
)

data class CodeDto(
    val id: Long,
    val keyword: String
)
