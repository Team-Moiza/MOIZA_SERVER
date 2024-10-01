package com.example.moiza.domain.community.post.domain

import com.example.moiza.domain.community.community.domain.CommunityRepository
import org.springframework.stereotype.Repository


@Repository
interface PostRepository : CommunityRepository<Post> {
}
