package com.example.moiza.domain.community.vote.domain

import com.example.moiza.domain.community.poll.domain.entity.PollOption
import com.example.moiza.domain.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VoteRepository : JpaRepository<Vote, Long> {
    fun findByPollOptionAndUser(pollOption: PollOption, user: User): Vote?
}
