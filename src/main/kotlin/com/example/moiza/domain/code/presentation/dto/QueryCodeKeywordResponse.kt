package com.example.moiza.domain.code.presentation.dto

data class QueryCodeKeywordResponse(
    val keyword: String
)

data class CodeResponse(
    val id: Long,
    val keyword: String
)
