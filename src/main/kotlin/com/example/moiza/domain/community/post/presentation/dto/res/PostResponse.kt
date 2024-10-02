package com.example.moiza.domain.community.post.presentation.dto.res

import java.time.LocalDate

class PostResponse(
    val postId: Long,
    val title: String,
    val content: String,
    val createdAt: LocalDate
)

data class PostIdResponse(
    val postId: Long
)
