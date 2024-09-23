package com.example.moiza.domain.auth.domain.repository

import com.example.moiza.domain.auth.domain.RefreshToken
import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository: CrudRepository<RefreshToken, String> {
}