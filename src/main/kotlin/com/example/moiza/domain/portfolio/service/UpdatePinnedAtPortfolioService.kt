package com.example.moiza.domain.portfolio.service

import com.example.moiza.domain.portfolio.domain.repository.PortfolioRepository
import com.example.moiza.domain.portfolio.exception.MissingPinFor24HoursException
import com.example.moiza.domain.portfolio.exception.PortfolioNotFoundException
import com.example.moiza.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Duration
import java.time.LocalDateTime

@Service
class UpdatePinnedAtPortfolioService(
    private val userFacade: UserFacade,
    private val portfolioRepository: PortfolioRepository,
) {
    @Transactional
    fun execute(id: Long) {
        val user = userFacade.getCurrentUser()
        val portfolio = portfolioRepository.findPortfolioByIdAndUser(id, user)
            ?: throw PortfolioNotFoundException

        val currentTime = LocalDateTime.now()
        val duration = Duration.between(portfolio.pinnedAt, currentTime)

        if (duration.toHours() < 24) {
            throw MissingPinFor24HoursException
        }

        portfolio.updatePinnedAt()
    }
}