package com.example.moiza.domain.like.presentation.dto.response

import com.example.moiza.domain.like.domain.Like
import com.example.moiza.domain.user.domain.type.Major
import com.example.moiza.domain.user.domain.type.School

data class LikesResponse(
    val portfolioId: Long,
    val name: String,
    val profile: String,
    val school: School?,
    val major: Major?,
    val introduce: String?,
    val likeCnt: Long,
) {
    companion object {
        fun from(like: Like): LikesResponse {
            val user = like.user
            return LikesResponse(
                portfolioId = like.portfolio.id,
                name = user.nickname,
                profile = user.profile,
                school = user.school,
                major = user.major,
                introduce = user.introduce,
                likeCnt = like.portfolio.likeCnt
            )
        }
    }
}
