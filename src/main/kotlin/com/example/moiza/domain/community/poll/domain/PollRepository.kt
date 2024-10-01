package com.example.moiza.domain.community.poll.domain

import com.example.moiza.domain.community.community.domain.CommunityRepository
import org.springframework.stereotype.Repository

@Repository
interface PollRepository : CommunityRepository<Poll> {
}
