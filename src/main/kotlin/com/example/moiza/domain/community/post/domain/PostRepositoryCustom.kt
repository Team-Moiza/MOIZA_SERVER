package com.example.moiza.domain.community.post.domain

interface PostRepositoryCustom {
    fun findById(postId: Long): Post?
}
