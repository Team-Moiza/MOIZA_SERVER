package com.example.moiza.domain.portfolio.domain.repository

import com.example.moiza.domain.portfolio.domain.Introduction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IntroductionRepository: JpaRepository<Introduction, Long> {
}