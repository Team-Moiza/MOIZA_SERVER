package com.example.moiza.domain.community.poll.domain

import com.example.moiza.domain.community.community.domain.Community
import com.example.moiza.domain.community.community.domain.type.CommunityType
import com.example.moiza.domain.user.domain.User
import jakarta.persistence.*
import java.time.LocalDate

@Entity(name = "poll")
class Poll(
    title: String,
    content: String,
    user: User,
    type: CommunityType,
    createdAt: LocalDate,
) : Community(
    title = title,
    content = content,
    user = user,
    type = type,
    createdAt = createdAt
) {
    @OneToMany(mappedBy = "poll", cascade = [CascadeType.ALL], orphanRemoval = true)
    var options: MutableList<PollOption> = mutableListOf()
        protected set

    fun addOptions(newOptions: List<PollOption>) {
        options.addAll(newOptions)
    }
}
