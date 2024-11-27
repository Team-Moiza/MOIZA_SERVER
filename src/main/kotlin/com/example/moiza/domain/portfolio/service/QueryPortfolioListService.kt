package com.example.moiza.domain.portfolio.service

import com.example.moiza.domain.portfolio.domain.repository.PortfolioRepository
import com.example.moiza.domain.portfolio.presentation.dto.res.PortfolioListResponse
import com.example.moiza.domain.user.facade.UserFacade
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class QueryPortfolioListService(
    private val portfolioRepository: PortfolioRepository,
    private val userFacade: UserFacade
) {
    @Transactional
    fun execute(): List<PortfolioListResponse> {
        val user = userFacade.getCurrentUser()
        return portfolioRepository.findPortfolioByUserId(user.id).map { portfolio ->
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
