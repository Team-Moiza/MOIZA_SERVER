package com.example.moiza.domain.portfolio.domain.repository

import com.example.moiza.domain.portfolio.domain.Portfolio
import com.example.moiza.domain.portfolio.domain.Qualification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QualificationRepository: JpaRepository<Qualification, Long> {
    fun findAllByPortfolio(portfolio: Portfolio): List<Qualification>
}
