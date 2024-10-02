package com.example.moiza.domain.community.post.service

import com.example.moiza.domain.community.post.domain.PostRepositoryCustom
import com.example.moiza.domain.community.post.exception.PostNotFoundException
import com.example.moiza.domain.community.post.presentation.dto.res.PostResponse
import com.example.moiza.domain.user.presentation.dto.res.UserResponse
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Transactional
@Service
class QueryPostDetailService(
    private val postRepositoryCustom: PostRepositoryCustom
) {

    fun execute(postId: Long): PostResponse {
        val post = postRepositoryCustom.findById(postId) ?: throw PostNotFoundException

        return PostResponse(
            postId = post.id,
            title = post.title,
            content = post.content,
            createdAt = post.createdAt,
            user = UserResponse(
                nickname = post.user.nickname,
                profile = post.user.profile
            ),
            image = post.image
        )
    }
}
