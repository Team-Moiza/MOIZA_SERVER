package com.example.moiza.domain.portfolio.presentation.dto.res

import com.example.moiza.domain.portfolio.presentation.dto.req.AwardDto
import com.example.moiza.domain.portfolio.presentation.dto.req.LinkDto
import com.example.moiza.domain.portfolio.presentation.dto.req.ProjectDto
import com.example.moiza.domain.portfolio.presentation.dto.req.QualificationDto
import com.example.moiza.domain.user.domain.type.Major
import com.example.moiza.domain.user.domain.type.School

data class PortfolioListResponse(
    val id: Long,
    val name: String,
    val school: School,
    val major: Major,
    val profile: String
)

data class PortfolioDetailResponse(
    val id: Long,
    val name: String,
    val major: Major,
    val projects: List<ProjectDto>?,
    val qualifications: List<QualificationDto>?,
    val awards: List<AwardDto>?,
    val links: List<LinkDto>?
)
