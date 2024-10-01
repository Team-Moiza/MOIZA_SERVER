package com.example.moiza.domain.community.poll.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PollOptionRepository : JpaRepository<PollOption, Long> {
}
