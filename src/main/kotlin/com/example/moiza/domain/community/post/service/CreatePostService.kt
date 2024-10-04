package com.example.moiza.domain.community.post.service

import com.example.moiza.domain.community.post.domain.entity.Post
import com.example.moiza.domain.community.post.domain.repository.PostRepository
import com.example.moiza.domain.community.post.presentation.dto.req.CreatePostRequest
import com.example.moiza.domain.community.post.presentation.dto.res.PostIdResponse
import com.example.moiza.domain.user.facade.UserFacade
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class CreatePostService(
    private val postRepository: PostRepository,
    private val userFacade: UserFacade,
) {

    fun execute(request: CreatePostRequest): PostIdResponse {
        val user = userFacade.getCurrentUser()

        val post = Post(
            title = request.title,
            content = request.content,
            user = user,
            image = request.image,
        )

        postRepository.save(post)

        return PostIdResponse(post.id)
    }
}
