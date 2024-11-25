package com.example.moiza.domain.portfolio.service

import com.example.moiza.domain.portfolio.domain.Portfolio
import com.example.moiza.domain.portfolio.domain.repository.PortfolioRepository
import com.example.moiza.domain.portfolio.presentation.dto.req.CreatePortfolioRequest
import com.example.moiza.domain.user.facade.UserFacade
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CreatePortfolioService(
    private val portfolioRepository: PortfolioRepository,
    private val userFacade: UserFacade
) {
    @Transactional
    fun execute(request: CreatePortfolioRequest) {
        val portfolio = Portfolio(user = userFacade.getCurrentUser())

        request.projects?.let { portfolio.addProjects(it) }
        request.qualifications?.let { portfolio.addQualifications(it) }
        request.awards?.let { portfolio.addAwards(it) }
        request.links?.let { portfolio.addLinks(it) }

        portfolioRepository.save(portfolio)
    }
}
