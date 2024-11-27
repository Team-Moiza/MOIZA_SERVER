package com.example.moiza.domain.portfolio.service

import com.example.moiza.domain.portfolio.domain.repository.PortfolioRepository
import com.example.moiza.domain.portfolio.exception.PortfolioNotFoundException
import com.example.moiza.domain.portfolio.presentation.dto.req.AwardDto
import com.example.moiza.domain.portfolio.presentation.dto.req.LinkDto
import com.example.moiza.domain.portfolio.presentation.dto.req.ProjectDto
import com.example.moiza.domain.portfolio.presentation.dto.req.QualificationDto
import com.example.moiza.domain.portfolio.presentation.dto.res.PortfolioDetailResponse
import com.example.moiza.domain.user.facade.UserFacade
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class QueryPortfolioDetailResponse(
    private val portfolioRepository: PortfolioRepository,
    private val userFacade: UserFacade
) {
    @Transactional
    fun execute(portfolioId: Long): PortfolioDetailResponse {
        val portfolio = portfolioRepository.findByIdOrNull(portfolioId) ?: throw PortfolioNotFoundException
        val user = userFacade.getCurrentUser()

        return PortfolioDetailResponse(
            id = portfolio.id,
            name = user.nickname,
            major = user.major!!,
            projects = portfolio.projects.map { project ->
                ProjectDto(
                    title = project.title,
                    status = project.status,
                    startDate = project.startDate,
                    endDate = project.endDate,
                    description = project.description,
                    link = project.link
                )
            },
            qualifications = portfolio.qualifications.map { qualification ->
                QualificationDto(
                    name = qualification.name,
                    score = qualification.score,
                    date = qualification.date
                )
            },
            awards = portfolio.awards.map { award ->
                AwardDto(
                    name = award.name,
                    type = award.type,
                    date = award.date,
                    description = award.description
                )
            },
            links = portfolio.links.map { link ->
                LinkDto(
                    url = link.url
                )
            }
        )
    }
}
