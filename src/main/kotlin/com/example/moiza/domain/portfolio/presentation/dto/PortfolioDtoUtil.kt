package com.example.moiza.domain.portfolio.presentation.dto

import com.example.moiza.domain.portfolio.domain.*
import com.example.moiza.domain.portfolio.presentation.dto.req.*

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

    fun getIntroductionDto(introduction: Introduction): IntroductionDto {
        return IntroductionDto(
            introduce = introduction.introduce,
            url = introduction.url
        )
    }
}
