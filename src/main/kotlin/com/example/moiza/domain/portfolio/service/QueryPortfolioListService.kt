package com.example.moiza.domain.portfolio.service

import com.example.moiza.domain.portfolio.domain.repository.PortfolioRepositoryCustom
import com.example.moiza.domain.portfolio.domain.type.UserStatus
import com.example.moiza.domain.portfolio.presentation.dto.PortfolioFilter
import com.example.moiza.domain.portfolio.presentation.dto.res.PortfolioListResponse
import com.example.moiza.domain.user.facade.UserFacade
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryPortfolioListService(
    private val userFacade: UserFacade,
    private val portfolioRepository: PortfolioRepositoryCustom,
) {
    @Transactional(readOnly = true)
    fun execute(pageable: Pageable, filter: PortfolioFilter): Page<PortfolioListResponse> {
        var status: UserStatus = UserStatus.NOT_LOGGED_IN

        if (userFacade.isLogin()) {
            status = userFacade.getCurrentUser().userStatus
        }

        return portfolioRepository.getPortfolioList(status, pageable, filter)
    }
}
