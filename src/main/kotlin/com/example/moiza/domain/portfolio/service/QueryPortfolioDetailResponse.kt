package com.example.moiza.domain.portfolio.service

import com.example.moiza.domain.portfolio.domain.repository.PortfolioRepository
import com.example.moiza.domain.portfolio.exception.PortfolioNotFoundException
import com.example.moiza.domain.portfolio.presentation.dto.PortfolioDtoUtil
import com.example.moiza.domain.portfolio.presentation.dto.res.PortfolioDetailResponse
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class QueryPortfolioDetailResponse(
    private val portfolioRepository: PortfolioRepository,
) {
    @Transactional
    fun execute(portfolioId: Long): PortfolioDetailResponse {
        val portfolio = portfolioRepository.findByIdOrNull(portfolioId) ?: throw PortfolioNotFoundException
        val user = portfolio.user

        return PortfolioDetailResponse(
            id = portfolio.id,
            name = user.nickname,
            major = user.major!!,
            introduce = portfolio.introduce,
            projects = portfolio.projects.map { project -> PortfolioDtoUtil.getProjectDto(project) },
            qualifications = portfolio.qualifications.map { qualification -> PortfolioDtoUtil.getQualificationDto(qualification) },
            awards = portfolio.awards.map { award -> PortfolioDtoUtil.getAwardDto(award) },
            links = portfolio.links.map { link -> PortfolioDtoUtil.getLinkDto(link) }
        )
    }
}
