package com.example.moiza.domain.portfolio.service

import com.example.moiza.domain.portfolio.domain.repository.PortfolioRepository
import com.example.moiza.domain.portfolio.domain.type.UserStatus
import com.example.moiza.domain.portfolio.exception.PortfolioNotFoundException
import com.example.moiza.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ChangePublishService(
    private val userFacade: UserFacade,
    private val portfolioRepository: PortfolioRepository,
) {
    @Transactional
    fun execute(): Boolean {
        val user = userFacade.getCurrentUser()
        val portfolio = (portfolioRepository.findPortfolioByUser(user)
            ?: throw PortfolioNotFoundException)

        if(portfolio.publish)
            user.updateUserStatus(UserStatus.PORTFOLIO_COMPLETED)
        else
            user.updateUserStatus(UserStatus.PORTFOLIO_PUBLISHED)

        return portfolio.changePublish()
    }
}