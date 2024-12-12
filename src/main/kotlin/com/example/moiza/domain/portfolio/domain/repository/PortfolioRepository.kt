package com.example.moiza.domain.portfolio.domain.repository

import com.example.moiza.domain.portfolio.domain.Portfolio
import com.example.moiza.domain.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PortfolioRepository: JpaRepository<Portfolio, Long> {
    fun findPortfolioByUser(user: User): Portfolio?
    fun findPortfolioByIdAndUser(portfolioId: Long, user: User): Portfolio?
}
