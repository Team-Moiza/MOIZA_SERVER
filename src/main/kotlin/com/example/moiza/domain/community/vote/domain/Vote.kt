package com.example.moiza.domain.community.vote.domain

import com.example.moiza.domain.community.poll.domain.entity.PollOption
import com.example.moiza.domain.user.domain.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GenerationType
import jakarta.persistence.ManyToOne
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity(name = "vote")
class Vote(
    pollOption: PollOption,
    user: User
) {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    val id: Long = 0L

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_option_id", nullable = false)
    var pollOption: PollOption = pollOption
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User = user
        protected set
}
