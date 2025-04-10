package com.example.moiza.domain.like.domain.repository

import com.example.moiza.domain.portfolio.domain.Portfolio
import com.example.moiza.domain.user.domain.User

interface LikeRepositoryCustom {
    fun findAllByUser(user: User): List<Portfolio>
}