package com.example.moiza.domain.portfolio.domain.repository

import com.example.moiza.domain.portfolio.domain.Award
import com.example.moiza.domain.portfolio.domain.Portfolio
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AwardRepository: JpaRepository<Award, Long> {
    fun findAllByPortfolio(portfolio: Portfolio): List<Award>
}
