package com.example.moiza.domain.community.poll.presentation.dto.res

import java.time.LocalDate

class PollResponse(
    val pollId: Long,
    val title: String,
    val content: String,
    val createdAt: LocalDate,
)

data class PollIdResponse(
    val pollId: Long
)
