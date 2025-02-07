package com.example.moiza.domain.code.domain.repository

import com.example.moiza.domain.code.domain.Code
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CodeRepository: JpaRepository<Code, Long> {
    fun findByKeyword(keyword: String): Code?
}
