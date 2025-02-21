package com.example.moiza.domain.like.service

import com.example.moiza.domain.like.domain.Like
import com.example.moiza.domain.like.domain.repository.LikeRepository
import com.example.moiza.domain.like.exception.AlreadyLikeException
import com.example.moiza.domain.portfolio.domain.repository.PortfolioRepository
import com.example.moiza.domain.portfolio.exception.PortfolioNotFoundException
import com.example.moiza.domain.user.facade.UserFacade
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LikeService(
    private val userFacade: UserFacade,
    private val likeRepository: LikeRepository,
    private val portfolioRepository: PortfolioRepository,
) {
    @Transactional
    fun execute(portfolioId: Long) {
        val user = userFacade.getCurrentUser()
        val portfolio = portfolioRepository.findByIdOrNull(portfolioId) ?: throw PortfolioNotFoundException

        likeRepository.findByUserAndPortfolio(user, portfolio)?.let { throw AlreadyLikeException }
        likeRepository.save(Like(portfolio, user))
        portfolio.like()
    }
}