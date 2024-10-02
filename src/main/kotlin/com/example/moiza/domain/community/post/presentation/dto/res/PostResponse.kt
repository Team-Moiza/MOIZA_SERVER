package com.example.moiza.domain.community.post.presentation.dto.res

import com.example.moiza.domain.user.presentation.dto.res.UserResponse
import java.time.LocalDate

class PostResponse(
    val postId: Long,
    val title: String,
    val content: String,
    val createdAt: LocalDate,
    val user: UserResponse,
    val image: List<String>? = null
)

data class PostsResponse(
    val posts: List<PostResponse>
)

data class PostIdResponse(
    val postId: Long
)
