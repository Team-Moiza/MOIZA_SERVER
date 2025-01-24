package com.example.moiza.domain.code.domain.repository

import com.example.moiza.domain.code.domain.PortfolioCode
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PortfolioCodeRepository: JpaRepository<PortfolioCode, Long> {
}
