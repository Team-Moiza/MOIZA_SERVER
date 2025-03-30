package com.example.moiza.domain.portfolio.domain.repository

import com.example.moiza.domain.portfolio.domain.Portfolio
import com.example.moiza.domain.portfolio.domain.type.UserStatus
import com.example.moiza.domain.portfolio.presentation.dto.PortfolioFilter
import com.example.moiza.domain.portfolio.presentation.dto.res.PortfolioListResponse
import org.springframework.data.domain.Page

interface PortfolioRepositoryCustom {
    fun getPortfolio(id: Long, status: UserStatus): Portfolio?
    fun getPortfolioList(status: UserStatus, filter: PortfolioFilter): Page<PortfolioListResponse>
}