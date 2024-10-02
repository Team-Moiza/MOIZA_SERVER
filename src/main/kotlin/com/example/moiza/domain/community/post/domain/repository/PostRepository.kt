package com.example.moiza.domain.community.post.domain.repository

import com.example.moiza.domain.community.community.domain.repository.CommunityRepository
import com.example.moiza.domain.community.post.domain.entity.Post
import com.example.moiza.domain.user.domain.User
import org.springframework.stereotype.Repository


@Repository
interface PostRepository : CommunityRepository<Post> {

    fun findByIdAndUser(postId: Long, user: User): Post?
}
