package com.example.moiza.domain.community.post.service

import com.example.moiza.domain.community.post.domain.repository.PostRepository
import com.example.moiza.domain.community.post.exception.PostNotFoundException
import com.example.moiza.domain.community.post.presentation.dto.res.PostIdResponse
import com.example.moiza.domain.user.facade.UserFacade
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class UpdatePostService(
    private val postRepository: PostRepository,
    private val userFacade: UserFacade
) {

    fun execute(title: String, content: String, postId: Long): PostIdResponse {
        val user = userFacade.getCurrentUser()
        val post = postRepository.findByIdAndUser(postId, user)
            ?: throw PostNotFoundException

        post.update(title, content)

        val updated = postRepository.save(post)

        return PostIdResponse(updated.id)
    }
}
