package com.example.moiza.domain.portfolio.presentation.dto

import com.example.moiza.domain.portfolio.domain.Project
import com.example.moiza.domain.portfolio.domain.Qualification
import com.example.moiza.domain.portfolio.domain.Award
import com.example.moiza.domain.portfolio.domain.Link
import com.example.moiza.domain.portfolio.presentation.dto.req.ProjectDto
import com.example.moiza.domain.portfolio.presentation.dto.req.QualificationDto
import com.example.moiza.domain.portfolio.presentation.dto.req.AwardDto
import com.example.moiza.domain.portfolio.presentation.dto.req.LinkDto

object PortfolioDtoUtil {
    fun getProjectDto(project: Project): ProjectDto {
        return ProjectDto(
            id = project.id,
            title = project.title,
            status = project.status,
            startDate = project.startDate,
            endDate = project.endDate,
            description = project.description,
            link = project.link
        )
    }

    fun getQualificationDto(qualification: Qualification): QualificationDto {
        return QualificationDto(
            id = qualification.id,
            name = qualification.name,
            score = qualification.score,
            date = qualification.date
        )
    }

    fun getAwardDto(award: Award): AwardDto {
        return AwardDto(
            id = award.id,
            name = award.name,
            type = award.type,
            date = award.date,
            description = award.description
        )
    }

    fun getLinkDto(link: Link): LinkDto {
        return LinkDto(
            id = link.id,
            url = link.url
        )
    }
}
