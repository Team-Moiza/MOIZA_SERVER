package com.example.moiza.domain.community.post.service

import com.example.moiza.domain.community.community.domain.type.CommunityType
import com.example.moiza.domain.community.post.domain.Post
import com.example.moiza.domain.community.post.domain.PostRepository
import com.example.moiza.domain.community.post.presentation.dto.req.CreatePostRequest
import com.example.moiza.domain.community.post.presentation.dto.res.CreatePostResponse
import com.example.moiza.domain.user.facade.UserFacade
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
@Transactional
class CreatePostService(
    private val postRepository: PostRepository,
    private val userFacade: UserFacade,
) {

    fun execute(request: CreatePostRequest): CreatePostResponse {
        val user = userFacade.getCurrentUser()

        val post = Post(
            title = request.title,
            content = request.content,
            user = user,
            type = CommunityType.POST,
            image = request.image,
            createdAt = LocalDate.now()
        )

        postRepository.save(post)

        return CreatePostResponse(post.id)
    }
}
