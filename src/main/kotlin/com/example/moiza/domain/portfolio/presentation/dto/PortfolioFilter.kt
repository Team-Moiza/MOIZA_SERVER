package com.example.moiza.domain.portfolio.presentation.dto

import com.example.moiza.domain.user.domain.type.School

data class PortfolioFilter(
    val code: List<Long>?,
    val school: School?,
    val isEmployed: Boolean?
)
