package com.example.moiza.domain.portfolio.domain.repository

import com.example.moiza.domain.portfolio.domain.QPortfolio.portfolio
import com.example.moiza.domain.portfolio.domain.type.UserStatus
import com.example.moiza.domain.portfolio.presentation.dto.PortfolioFilter
import com.example.moiza.domain.portfolio.presentation.dto.res.PortfolioListResponse
import com.example.moiza.domain.user.domain.type.School
import com.querydsl.core.types.Projections.constructor
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQuery
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.support.PageableExecutionUtils
import org.springframework.stereotype.Repository

@Repository
class PortfolioRepositoryImpl(
    private val queryFactory: JPAQueryFactory
): PortfolioRepositoryCustom {
    override fun getPortfolioList(status: UserStatus, pageable: Pageable, filter: PortfolioFilter): Page<PortfolioListResponse> {
        val fetch = queryFactory
            .select(
                constructor(
                    PortfolioListResponse::class.java,
                    portfolio.id,
                    portfolio.user.nickname,
                    portfolio.user.school,
                    portfolio.user.major,
                    portfolio.user.profile,
                    portfolio.user.introduce
                )
            )
            .from(portfolio)
            .where(portfolio.userStatus.loe(status.level)
                .and(portfolio.isPublished.isTrue)
                .and(containsCodes(filter.code))
                .and(eqIsEmployed(filter.isEmployed))
                .and(eqSchool(filter.school))
            )
            .fetch()

        val countQuery: JPAQuery<Long> = queryFactory
            .select(portfolio.count())
            .from(portfolio)
            .where(portfolio.userStatus.loe(status.level)
                .and(containsCodes(filter.code))
                .and(eqIsEmployed(filter.isEmployed))
                .and(eqSchool(filter.school))
            )

        return PageableExecutionUtils.getPage(fetch, pageable) { countQuery.fetchOne() ?: 0L }
    }

    private fun containsCodes(codes: List<Long>?): BooleanExpression? {
        return codes.takeIf { !it.isNullOrEmpty() }?.let { portfolio._portfolioCodes.any().code.id.`in`(it) }
    }

    private fun eqSchool(school: School?): BooleanExpression? {
        return school?.let { portfolio.user.school.eq(it) }
    }

    private fun eqIsEmployed(isEmployed: Boolean?): BooleanExpression? {
        return when (isEmployed) {
            true -> portfolio.user.company.isNull
            false -> portfolio.user.company.isNotNull()
            else -> null
        }
    }
}
