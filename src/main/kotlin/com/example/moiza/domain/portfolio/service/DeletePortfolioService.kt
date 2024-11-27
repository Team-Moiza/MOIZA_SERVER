package com.example.moiza.domain.portfolio.service

import com.example.moiza.domain.portfolio.domain.repository.PortfolioRepository
import com.example.moiza.domain.portfolio.exception.PortfolioNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class DeletePortfolioService(
    private val portfolioRepository: PortfolioRepository
) {
    fun execute(portfolioId: Long) {
        val portfolio = portfolioRepository.findByIdOrNull(portfolioId) ?: throw PortfolioNotFoundException

        portfolioRepository.delete(portfolio)
    }
}
