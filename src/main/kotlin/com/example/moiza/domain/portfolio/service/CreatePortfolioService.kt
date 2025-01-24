package com.example.moiza.domain.portfolio.service

import com.example.moiza.domain.code.domain.repository.CodeRepository
import com.example.moiza.domain.portfolio.domain.Portfolio
import com.example.moiza.domain.portfolio.domain.repository.PortfolioRepository
import com.example.moiza.domain.portfolio.domain.type.UserStatus
import com.example.moiza.domain.portfolio.presentation.dto.req.PortfolioRequest
import com.example.moiza.domain.user.facade.UserFacade
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CreatePortfolioService(
    private val portfolioRepository: PortfolioRepository,
    private val userFacade: UserFacade,
    private val codeRepository: CodeRepository,
) {
    @Transactional
    fun execute(request: PortfolioRequest) {
        val user = userFacade.getCurrentUser()
        val portfolio = Portfolio(user)

        request.projects?.let(portfolio::addProjects)
        request.qualifications?.let(portfolio::addQualifications)
        request.awards?.let(portfolio::addAwards)
        request.links?.let(portfolio::addLinks)
        request.introduction?.let(portfolio::addIntroduction)
        request.codes?.let { codeIds ->
            val codes = codeRepository.findAllById(codeIds)
            codes.forEach { code ->
                portfolio.addCode(code)
            }
        }

        user.updateUserStatus(UserStatus.PORTFOLIO_COMPLETED)
        portfolioRepository.save(portfolio)
    }
}
