package com.example.moiza.domain.portfolio.domain.repository

import com.example.moiza.domain.portfolio.domain.Link
import com.example.moiza.domain.portfolio.domain.Portfolio
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LinkRepository: JpaRepository<Link, Long> {
    fun findAllByPortfolio(portfolio: Portfolio): List<Link>
}
