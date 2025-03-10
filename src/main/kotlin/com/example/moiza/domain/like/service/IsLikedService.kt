package com.example.moiza.domain.like.service

import com.example.moiza.domain.like.domain.repository.LikeRepository
import com.example.moiza.domain.portfolio.domain.repository.PortfolioRepository
import com.example.moiza.domain.portfolio.exception.PortfolioNotFoundException
import com.example.moiza.domain.user.facade.UserFacade
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class IsLikedService(
    private val userFacade: UserFacade,
    private val likeRepository: LikeRepository,
    private val portfolioRepository: PortfolioRepository,
) {
    @Transactional
    fun execute(portfolioId: Long): Boolean {
        val user = userFacade.getCurrentUser()
        val portfolio = portfolioRepository.findByIdOrNull(portfolioId) ?: throw PortfolioNotFoundException

        likeRepository.findByUserAndPortfolio(user, portfolio)?.let { return true }
        return false
    }
}