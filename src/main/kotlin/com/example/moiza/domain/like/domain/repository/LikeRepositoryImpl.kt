package com.example.moiza.domain.like.domain.repository

import com.example.moiza.domain.like.domain.QLike.like
import com.example.moiza.domain.portfolio.domain.Portfolio
import com.example.moiza.domain.portfolio.domain.QPortfolio.portfolio
import com.example.moiza.domain.user.domain.User
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class LikeRepositoryImpl(
    private val queryFactory: JPAQueryFactory
): LikeRepositoryCustom {
    override fun findAllByUser(user: User): List<Portfolio> {
        val portfolios = queryFactory
            .selectFrom(like)
            .where(like.user.eq(user))
            .fetch()
            .map { it.portfolio }

        return queryFactory
            .selectFrom(portfolio)
            .where(portfolio.`in`(portfolios)
                .and(portfolio.isPublished))
            .fetch()
    }
}