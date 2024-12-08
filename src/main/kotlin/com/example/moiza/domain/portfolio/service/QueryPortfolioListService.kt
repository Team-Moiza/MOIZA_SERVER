package com.example.moiza.domain.portfolio.service

import com.example.moiza.domain.portfolio.domain.repository.PortfolioRepository
import com.example.moiza.domain.portfolio.presentation.dto.res.PortfolioListResponse
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class QueryPortfolioListService(
    private val portfolioRepository: PortfolioRepository,
) {
    @Transactional
    fun execute(): List<PortfolioListResponse> {
        val portfolios = portfolioRepository.findAll()

        return portfolios.map { portfolio ->
            val user = portfolio.user

            PortfolioListResponse(
                id = portfolio.id,
                name = user.nickname,
                school = user.school!!,
                major = user.major!!,
                profile = user.profile
            )
        }
    }
}
