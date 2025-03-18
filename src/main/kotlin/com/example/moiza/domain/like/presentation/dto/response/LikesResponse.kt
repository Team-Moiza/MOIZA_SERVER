package com.example.moiza.domain.like.presentation.dto.response

import com.example.moiza.domain.code.presentation.dto.CodeResponse
import com.example.moiza.domain.like.domain.Like
import com.example.moiza.domain.user.domain.type.Job
import com.example.moiza.domain.user.domain.type.Major
import com.example.moiza.domain.user.domain.type.School
import java.time.LocalDate

data class LikesResponse(
    val portfolioId: Long,
    val name: String,
    val company: String?,
    val profile: String,
    val enrollmentStartDate: LocalDate?,
    val job: Job?,
    val school: School?,
    val major: Major?,
    val introduce: String?,
    val likeCnt: Long,
    val codes: List<CodeResponse>
) {
    companion object {
        fun from(like: Like): LikesResponse {
            val user = like.user
            return LikesResponse(
                portfolioId = like.portfolio.id,
                name = user.nickname,
                company = user.company,
                enrollmentStartDate = user.enrollmentStartDate,
                job = user.job,
                profile = user.profile,
                school = user.school,
                major = user.major,
                introduce = user.introduce,
                likeCnt = like.portfolio.likeCnt,
                codes = like.portfolio.codes.map { portfolioCode ->
                    CodeResponse(
                        id = portfolioCode.code.id,
                        keyword = portfolioCode.code.keyword
                    )
                },
            )
        }
    }
}
