package com.example.moiza.domain.code.service

import com.example.moiza.domain.code.domain.repository.CodeRepository
import com.example.moiza.domain.code.exception.CodeNotFoundException
import com.example.moiza.domain.code.presentation.dto.QueryCodeKeywordResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryCodeKeywordService(
    private val codeRepository: CodeRepository,
) {
    @Transactional(readOnly = true)
    fun execute(codeId: Long): QueryCodeKeywordResponse {
        val code = codeRepository.findByIdOrNull(codeId) ?: throw CodeNotFoundException

        return QueryCodeKeywordResponse(keyword = code.keyword)
    }
}
