package com.example.moiza.domain.community.post.service

import com.example.moiza.domain.community.community.exception.AccessDeniedException
import com.example.moiza.domain.community.post.domain.repository.PostRepository
import com.example.moiza.domain.community.post.exception.PostNotFoundException
import com.example.moiza.domain.user.facade.UserFacade
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Transactional
@Service
class DeletePostService(
    private val postRepository: PostRepository,
    private val userFacade: UserFacade
) {

    fun execute(postId: Long) {
        val user = userFacade.getCurrentUser()

        val post = postRepository.findByIdOrNull(postId) ?: throw PostNotFoundException

        if (post.user != user) {
            throw AccessDeniedException
        }

        postRepository.delete(post)
    }
}
