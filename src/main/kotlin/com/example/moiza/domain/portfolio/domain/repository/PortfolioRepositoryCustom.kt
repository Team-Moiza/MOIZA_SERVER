package com.example.moiza.domain.portfolio.domain.repository

import com.example.moiza.domain.portfolio.domain.Portfolio
import com.example.moiza.domain.portfolio.domain.type.UserStatus
import com.example.moiza.domain.portfolio.presentation.dto.PortfolioFilter
import com.example.moiza.domain.portfolio.presentation.dto.res.PortfolioListResponse
import com.example.moiza.domain.user.domain.User
import org.springframework.data.domain.Page

interface PortfolioRepositoryCustom {
    fun getPortfolio(id: Long, user: User?, status: UserStatus): Portfolio?
    fun getPortfolioList(status: UserStatus, filter: PortfolioFilter): Page<PortfolioListResponse>
}