package com.example.moiza.domain.community.poll.domain

import com.example.moiza.domain.community.community.domain.CommunityRepository
import com.example.moiza.domain.user.domain.User
import org.springframework.stereotype.Repository

@Repository
interface PollRepository : CommunityRepository<Poll> {

    fun findByIdAndUser(pollId: Long, user: User): Poll?
}
