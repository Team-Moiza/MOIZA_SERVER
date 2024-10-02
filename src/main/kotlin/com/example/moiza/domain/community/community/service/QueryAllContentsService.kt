package com.example.moiza.domain.community.community.service

import com.example.moiza.domain.community.community.presentation.dto.res.CommunityResponse
import com.example.moiza.domain.community.poll.domain.PollRepositoryCustom
import com.example.moiza.domain.community.post.domain.PostRepositoryCustom
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QueryAllContentsService(
    private val postRepositoryCustom: PostRepositoryCustom,
    private val pollRepositoryCustom: PollRepositoryCustom
) {
    fun execute(): CommunityResponse {
        val posts = postRepositoryCustom.findAllPosts()
        val polls = pollRepositoryCustom.findAllPolls()

        return CommunityResponse(posts, polls)
    }
}
