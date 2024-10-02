package com.example.moiza.domain.community.poll.domain.repository

import com.example.moiza.domain.community.community.domain.repository.CommunityRepository
import com.example.moiza.domain.community.poll.domain.entity.Poll
import com.example.moiza.domain.user.domain.User
import org.springframework.stereotype.Repository

@Repository
interface PollRepository : CommunityRepository<Poll> {

    fun findByIdAndUser(pollId: Long, user: User): Poll?
}
