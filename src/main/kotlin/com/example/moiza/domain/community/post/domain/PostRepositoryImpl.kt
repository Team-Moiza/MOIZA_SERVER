package com.example.moiza.domain.community.post.domain

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class PostRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : PostRepositoryCustom {

    override fun findById(postId: Long): Post? {
        val post = QPost.post

        return queryFactory
            .selectFrom(post)
            .leftJoin(post.image)
            .fetchJoin()
            .where(post.id.eq(postId))
            .fetchOne()
    }
}
