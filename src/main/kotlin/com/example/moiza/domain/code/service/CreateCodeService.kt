package com.example.moiza.domain.code.service

import com.example.moiza.domain.code.domain.Code
import com.example.moiza.domain.code.domain.repository.CodeRepository
import com.example.moiza.domain.code.presentation.dto.CodeResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateCodeService(
    private val codeRepository: CodeRepository,
) {
    @Transactional
    fun execute(keyword: String): CodeResponse {
        val code = codeRepository.findByKeyword(keyword) ?: Code(keyword = keyword)
        val savedCode = codeRepository.save(code)

        return CodeResponse(savedCode.id, savedCode.keyword)
    }
}
