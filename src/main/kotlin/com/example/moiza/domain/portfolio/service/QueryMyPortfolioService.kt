package com.example.moiza.domain.portfolio.service

import com.example.moiza.domain.portfolio.domain.repository.PortfolioRepository
import com.example.moiza.domain.portfolio.presentation.dto.res.MyPortfolioResponse
import com.example.moiza.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryMyPortfolioService(
    private val portfolioRepository: PortfolioRepository,
    private val userFacade: UserFacade
) {
    @Transactional
    fun execute(): List<MyPortfolioResponse>? {
        val user = userFacade.getCurrentUser()
        val portfolios = portfolioRepository.findAllByUser(user) ?: return null

        return portfolios.map { portfolio ->
            MyPortfolioResponse(
                id = portfolio.id,
                title = portfolio.title,
                isPublished = portfolio.isPublished,
                updatedAt = portfolio.updatedAt
            )
        }
    }
}
