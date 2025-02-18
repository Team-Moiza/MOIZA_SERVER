package com.example.moiza.domain.portfolio.domain.repository

import com.example.moiza.domain.portfolio.domain.type.UserStatus
import com.example.moiza.domain.portfolio.presentation.dto.PortfolioFilter
import com.example.moiza.domain.portfolio.presentation.dto.res.PortfolioListResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PortfolioRepositoryCustom {
    fun getPortfolioList(status: UserStatus, pageable: Pageable, filter: PortfolioFilter): Page<PortfolioListResponse>
}