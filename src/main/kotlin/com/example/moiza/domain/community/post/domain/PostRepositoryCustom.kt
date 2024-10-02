package com.example.moiza.domain.community.post.domain

import com.example.moiza.domain.community.post.presentation.dto.res.PostResponse

interface PostRepositoryCustom {
    fun findById(postId: Long): Post?

    fun findAllPosts(): List<PostResponse>
}
