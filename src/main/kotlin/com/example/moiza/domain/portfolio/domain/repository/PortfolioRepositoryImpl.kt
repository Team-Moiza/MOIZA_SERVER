package com.example.moiza.domain.portfolio.domain.repository

import com.example.moiza.domain.code.domain.QPortfolioCode.portfolioCode
import com.example.moiza.domain.portfolio.domain.QPortfolio.portfolio
import com.example.moiza.domain.portfolio.domain.type.UserStatus
import com.example.moiza.domain.portfolio.presentation.dto.PortfolioFilter
import com.example.moiza.domain.portfolio.presentation.dto.res.PortfolioListResponse
import com.example.moiza.domain.user.domain.type.School
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.impl.JPAQuery
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.support.PageableExecutionUtils
import org.springframework.stereotype.Repository


@Repository
class PortfolioRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : PortfolioRepositoryCustom {
    override fun getPortfolioList(status: UserStatus, filter: PortfolioFilter): Page<PortfolioListResponse> {
        val portfolios = queryFactory
            .selectFrom(portfolio)
            .where(
                portfolio.userStatus.loe(status.level)
                    .and(portfolio.isPublished.isTrue)
                    .and(containsCodes(filter.code))
                    .and(eqIsEmployed(filter.isEmployed))
                    .and(eqSchool(filter.school))
            )
            .fetch()

        val portfolioResult = portfolios.map { portfolio ->
            val fetch = queryFactory.select(portfolioCode.code)
                .from(portfolioCode)
                .where(portfolioCode.portfolio.eq(portfolio))
                .fetch()

            PortfolioListResponse(
                portfolio.id,
                portfolio.user.nickname,
                portfolio.user.school,
                portfolio.user.company,
                portfolio.user.major,
                portfolio.user.job,
                portfolio.user.profile,
                portfolio.user.introduce,
                portfolio.likeCnt,
                fetch
            )
        }

        val countQuery: JPAQuery<Long> = queryFactory
            .select(portfolio.count())
            .from(portfolio)
            .where(
                portfolio.userStatus.loe(status.level)
                    .and(portfolio.isPublished.isTrue)
                    .and(containsCodes(filter.code))
                    .and(eqIsEmployed(filter.isEmployed))
                    .and(eqSchool(filter.school))
            )

        return PageableExecutionUtils.getPage(portfolioResult, filter.getPageable()) { countQuery.fetchOne() ?: 0L }
    }

    private fun containsCodes(codes: List<Long>?): BooleanExpression {
        return if (!codes.isNullOrEmpty()) {
            portfolio._portfolioCodes.any().code.id.`in`(codes)
        } else {
            Expressions.TRUE
        }
    }

    private fun eqSchool(school: School?): BooleanExpression {
        return school?.let { portfolio.user.school.eq(it) } ?: Expressions.TRUE
    }

    private fun eqIsEmployed(isEmployed: Boolean?): BooleanExpression {
        return when (isEmployed) {
            true -> portfolio.user.company.isNull
            false -> portfolio.user.company.isNotNull()
            else -> Expressions.TRUE
        }
    }
}
