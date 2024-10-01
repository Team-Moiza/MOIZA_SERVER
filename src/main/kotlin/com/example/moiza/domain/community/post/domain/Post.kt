package com.example.moiza.domain.community.post.domain

import com.example.moiza.domain.community.community.domain.Community
import com.example.moiza.domain.community.community.domain.type.CommunityType
import com.example.moiza.domain.user.domain.User
import jakarta.persistence.Entity
import jakarta.persistence.ElementCollection
import java.time.LocalDate

@Entity(name = "post")
class Post(
    title: String,
    content: String,
    user: User,
    type: CommunityType,
    image: List<String>? = null,
    createdAt: LocalDate
) : Community(
    title = title,
    content = content,
    user = user,
    type = type,
    createdAt = createdAt
) {

    @ElementCollection
    var image: List<String>? = image
        protected set
}
