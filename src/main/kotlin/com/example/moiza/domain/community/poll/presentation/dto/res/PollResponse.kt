package com.example.moiza.domain.community.poll.presentation.dto.res

import com.example.moiza.domain.user.presentation.dto.res.UserResponse

class PollResponse(
    val pollId: Long,
    val title: String,
    val content: String,
    val options: List<PollOptionResponse>,
    val user: UserResponse
)

data class PollOptionResponse(
    val pollOptionId: Long,
    val description: String,
    val voteCount: Int,
    val votePercentage: Double
)

data class PollIdResponse(
    val pollId: Long
)
