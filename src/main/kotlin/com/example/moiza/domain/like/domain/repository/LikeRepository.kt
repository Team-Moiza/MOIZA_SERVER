package com.example.moiza.domain.like.domain.repository

import com.example.moiza.domain.like.domain.Like
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LikeRepository : JpaRepository<Like, Long> {
}