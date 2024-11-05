package com.example.moiza.domain.community.post.presentation.dto.res

import com.example.moiza.domain.user.presentation.dto.res.UserResponse

class PostDetailResponse(
    val postId: Long,
    val title: String,
    val content: String,
    val image: List<String>? = null,
    val user: UserResponse,
)

data class PostsResponse(
    val posts: List<PostDetailResponse>
)

data class PostIdResponse(
    val postId: Long
)

class PostResponse(
    val postId: Long,
    val title: String,
    val content: String,
    val user: UserResponse,
)
