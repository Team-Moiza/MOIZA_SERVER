package com.example.moiza.domain.portfolio.presentation.dto

import com.example.moiza.domain.user.domain.type.School
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

data class PortfolioFilter(
    val page: Int = 0,
    val size: Int = 20,
    val code: List<Long>? = null,
    val school: School? = null,
    val isEmployed: Boolean? = null,
) {
    fun getPageable(): Pageable = PageRequest.of(page, size)
}
