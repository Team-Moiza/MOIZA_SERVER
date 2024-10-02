package com.example.moiza.domain.community.poll.domain.repository

import com.example.moiza.domain.community.poll.domain.entity.PollOption
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PollOptionRepository : JpaRepository<PollOption, Long> {
}
