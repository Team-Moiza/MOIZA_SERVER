package com.example.moiza.domain.community.community.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommunityRepository<T : Community> : JpaRepository<T, Long> {

}
