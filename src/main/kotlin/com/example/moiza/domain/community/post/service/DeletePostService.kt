package com.example.moiza.domain.community.post.service

import com.example.moiza.domain.community.poll.exception.PollNotFoundException
import com.example.moiza.domain.community.post.domain.PostRepository
import com.example.moiza.domain.community.post.exception.PostNotFoundException
import com.example.moiza.domain.user.facade.UserFacade
import com.teaminsert.homepage.global.security.jwt.exception.InvalidJwtException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Transactional
@Service
class DeletePostService(
    private val postRepository: PostRepository,
    private val userFacade: UserFacade
) {

    fun execute(postId: Long) {
        val user = userFacade.getCurrentUser()

        val post = postRepository.findById(postId)
            .orElseThrow { PostNotFoundException }

        if (post.user != user) {
            throw InvalidJwtException
        }

        postRepository.delete(post)
    }
}
