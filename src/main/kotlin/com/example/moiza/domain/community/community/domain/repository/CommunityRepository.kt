package com.example.moiza.domain.community.community.domain.repository

import com.example.moiza.domain.community.community.domain.entity.Community
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommunityRepository<T : Community> : JpaRepository<T, Long> {

}
