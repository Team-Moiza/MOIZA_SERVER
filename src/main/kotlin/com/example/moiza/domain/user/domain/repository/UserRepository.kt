package com.example.moiza.domain.user.domain.repository

import com.example.moiza.domain.user.domain.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Long> {
    fun findByEmail(email: String): User?
}