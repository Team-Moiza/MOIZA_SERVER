package com.example.moiza.domain.portfolio.service

import com.example.moiza.domain.code.presentation.dto.CodeResponse
import com.example.moiza.domain.portfolio.domain.repository.PortfolioRepositoryCustom
import com.example.moiza.domain.portfolio.domain.type.UserStatus
import com.example.moiza.domain.portfolio.exception.PortfolioNotFoundException
import com.example.moiza.domain.portfolio.presentation.dto.PortfolioDtoUtil
import com.example.moiza.domain.portfolio.presentation.dto.res.PortfolioDetailResponse
import com.example.moiza.domain.user.domain.User
import com.example.moiza.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryPortfolioDetailService(
    private val portfolioRepository: PortfolioRepositoryCustom,
    private val userFacade: UserFacade,
) {
    @Transactional
    fun execute(portfolioId: Long): PortfolioDetailResponse {
        var status: UserStatus = UserStatus.NOT_LOGGED_IN
        var user: User? = null

        if (userFacade.isLogin()) {
            user = userFacade.getCurrentUser()
            status = user.userStatus
        }

        val portfolio = portfolioRepository.getPortfolio(portfolioId, user, status)
            ?: throw PortfolioNotFoundException
        val portfolioUser = portfolio.user

        return PortfolioDetailResponse(
            id = portfolio.id,
            name = portfolioUser.nickname,
            profile = portfolioUser.profile,
            job = portfolioUser.job,
            school = portfolioUser.school,
            company = portfolioUser.company,
            introduce = portfolioUser.introduce,
            educationStatus = portfolioUser.educationStatus,
            enrollmentStartDate = portfolioUser.enrollmentStartDate,
            major = portfolioUser.major,
            introduction = portfolio.introduction?.let { PortfolioDtoUtil.getIntroductionDto(it) },
            projects = portfolio.projects.map { project -> PortfolioDtoUtil.getProjectDto(project) },
            qualifications = portfolio.qualifications.map { qualification -> PortfolioDtoUtil.getQualificationDto(qualification) },
            awards = portfolio.awards.map { award -> PortfolioDtoUtil.getAwardDto(award) },
            links = portfolio.links.map { link -> PortfolioDtoUtil.getLinkDto(link) },
            codes = portfolio.codes.map { portfolioCode ->
                CodeResponse(
                    id = portfolioCode.code.id,
                    keyword = portfolioCode.code.keyword
                )
            },
            likeCnt = portfolio.likeCnt,
        )
    }
}
