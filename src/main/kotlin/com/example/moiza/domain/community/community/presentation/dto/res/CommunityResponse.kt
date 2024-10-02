package com.example.moiza.domain.community.community.presentation.dto.res

import com.example.moiza.domain.community.poll.presentation.dto.res.PollResponse
import com.example.moiza.domain.community.post.presentation.dto.res.PostResponse

data class CommunityResponse(
    val posts: List<PostResponse>,
    val polls: List<PollResponse>
)
