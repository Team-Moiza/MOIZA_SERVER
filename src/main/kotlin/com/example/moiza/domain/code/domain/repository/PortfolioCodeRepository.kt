package com.example.moiza.domain.code.domain.repository

import com.example.moiza.domain.code.domain.PortfolioCode
import com.example.moiza.domain.portfolio.domain.Portfolio
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PortfolioCodeRepository: JpaRepository<PortfolioCode, Long> {
    fun findAllByPortfolio(portfolio: Portfolio): List<PortfolioCode>
}
