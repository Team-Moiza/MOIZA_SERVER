package com.example.moiza.domain.portfolio.presentation.dto.req

import java.time.LocalDate

data class CreatePortfolioRequest(
    val projects: List<ProjectDto>? = null,
    val awards: List<AwardDto>? = null,
    val qualifications: List<QualificationDto>? = null,
    val links: List<LinkDto>? = null,
)

data class ProjectDto(
    val title: String,
    val status: Boolean,
    val startDate: LocalDate?,
    val endDate: LocalDate?,
    val description: String,
    val link: String
)

data class AwardDto(
    val name: String,
    val type: String,
    val date: LocalDate,
    val description: String
)

data class QualificationDto(
    val name: String,
    val score: String,
    val date: LocalDate
)

data class LinkDto(
    val url: String
)
