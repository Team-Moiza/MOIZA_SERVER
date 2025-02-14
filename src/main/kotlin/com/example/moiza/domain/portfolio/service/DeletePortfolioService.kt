package com.example.moiza.domain.portfolio.service

import com.example.moiza.domain.portfolio.domain.repository.PortfolioRepository
import com.example.moiza.domain.portfolio.exception.PortfolioNotFoundException
import com.example.moiza.domain.user.facade.UserFacade
import org.springframework.stereotype.Service

@Service
class DeletePortfolioService(
    private val portfolioRepository: PortfolioRepository,
    private val userFacade: UserFacade
) {
    fun execute(portfolioId: Long) {
        val user = userFacade.getCurrentUser()
        val portfolio = portfolioRepository.findPortfolioByIdAndUser(portfolioId, user)
            ?: throw PortfolioNotFoundException

        portfolioRepository.delete(portfolio)
    }
}
