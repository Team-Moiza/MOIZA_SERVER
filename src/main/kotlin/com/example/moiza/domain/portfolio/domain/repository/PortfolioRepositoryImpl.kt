package com.example.moiza.domain.portfolio.domain.repository

import com.example.moiza.domain.portfolio.domain.QPortfolio.portfolio
import com.example.moiza.domain.portfolio.domain.type.UserStatus
import com.example.moiza.domain.portfolio.presentation.dto.res.PortfolioListResponse
import com.querydsl.core.types.Projections.constructor
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
    override fun getPortfolioList(status: UserStatus, pageable: Pageable): Page<PortfolioListResponse> {
        val fetch = queryFactory
            .select(
                constructor(
                    PortfolioListResponse::class.java,
                    portfolio.id,
                    portfolio.user.nickname,
                    portfolio.user.school,
                    portfolio.user.major,
                    portfolio.user.profile
                )
            )
            .from(portfolio)
            .where(portfolio.userStatus.loe(status.level)
                .and(portfolio.isPublished.isTrue))
            .fetch()

        val countQuery: JPAQuery<Long> = queryFactory
            .select(portfolio.count())
            .from(portfolio)
            .where(portfolio.userStatus.loe(status.level))

        return PageableExecutionUtils.getPage(fetch, pageable) { countQuery.fetchOne() ?: 0L }
    }
}