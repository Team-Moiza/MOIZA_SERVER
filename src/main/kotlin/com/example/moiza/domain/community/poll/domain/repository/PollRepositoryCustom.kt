package com.example.moiza.domain.community.poll.domain.repository

import com.example.moiza.domain.community.poll.presentation.dto.res.PollResponse

interface PollRepositoryCustom {
    fun findAllPolls(): List<PollResponse>
}
