package com.example.moiza.domain.portfolio.service

import com.example.moiza.domain.code.domain.repository.CodeRepository
import com.example.moiza.domain.portfolio.domain.Portfolio
import com.example.moiza.domain.portfolio.domain.Project
import com.example.moiza.domain.portfolio.domain.repository.PortfolioRepository
import com.example.moiza.domain.portfolio.domain.type.UserStatus
import com.example.moiza.domain.portfolio.exception.MissingProfileForPortfolioException
import com.example.moiza.domain.portfolio.presentation.dto.req.PortfolioRequest
import com.example.moiza.domain.user.facade.UserFacade
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.stereotype.Service

@Service
class CreatePortfolioService(
    private val portfolioRepository: PortfolioRepository,
    private val userFacade: UserFacade,
    private val codeRepository: CodeRepository,
) {
    @Transactional
    fun execute(request: @Valid PortfolioRequest): Long {
        val user = userFacade.getCurrentUser()

        if (user.userStatus == UserStatus.LOGGED_IN) {
            throw MissingProfileForPortfolioException
        }

        val portfolio = Portfolio(user, request.title)

        val projects = request.projects.map { dto ->
            val project = Project(
                portfolio = portfolio,
                title = dto.title,
                status = dto.status,
                startDate = dto.startDate!!,
                endDate = dto.endDate!!,
                description = dto.description,
                link = dto.link
            )

            dto.codes?.let { codeIds ->
                val codes = codeRepository.findAllById(codeIds)
                codes.forEach { project.addCode(it) }
            }

            project
        } ?: emptyList()

        portfolio.addProjects(projects)

        request.qualifications?.let(portfolio::addQualifications)
        request.awards?.let(portfolio::addAwards)
        request.links?.let(portfolio::addLinks)
        request.introduction?.let(portfolio::addIntroduction)
        request.codes.let { dto ->
            val codeIds = dto.map { code -> code.id }
            val codes = codeRepository.findAllById(codeIds)
            codes.forEach { portfolio.addCode(it) }
        }

        user.updateUserStatus(UserStatus.PORTFOLIO_COMPLETED)
        return portfolioRepository.save(portfolio).id
    }
}
