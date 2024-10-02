package com.example.moiza.domain.community.poll.domain

import com.example.moiza.domain.community.poll.presentation.dto.res.PollResponse

interface PollRepositoryCustom {
    fun findAllPolls(): List<PollResponse>
}
