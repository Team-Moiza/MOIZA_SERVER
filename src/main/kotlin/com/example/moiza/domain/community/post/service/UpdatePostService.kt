package com.example.moiza.domain.community.post.service

import com.example.moiza.domain.community.community.exception.AccessDeniedException
import com.example.moiza.domain.community.post.domain.repository.PostRepository
import com.example.moiza.domain.community.post.exception.PostNotFoundException
import com.example.moiza.domain.community.post.presentation.dto.res.PostIdResponse
import com.example.moiza.domain.user.facade.UserFacade
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Transactional
class UpdatePostService(
    private val postRepository: PostRepository,
    private val userFacade: UserFacade
) {

    fun execute(title: String, content: String, postId: Long): PostIdResponse {
        val user = userFacade.getCurrentUser()
        val post = postRepository.findByIdOrNull(postId) ?: throw PostNotFoundException

        if (!post.checkAuthority(user)) { AccessDeniedException }

        post.update(title, content)

        return PostIdResponse(post.id)
    }
}
