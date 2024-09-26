package com.example.moiza.domain.auth.domain.repository

import com.example.moiza.domain.auth.domain.RefreshToken
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository: CrudRepository<RefreshToken, String> {
}