package com.example.moiza.domain.portfolio.domain.repository

import com.example.moiza.domain.portfolio.domain.Portfolio
import com.example.moiza.domain.portfolio.domain.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository: JpaRepository<Project, Long> {
    fun findAllByPortfolio(portfolio: Portfolio): List<Project>
}
