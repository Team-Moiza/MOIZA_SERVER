package com.example.moiza.domain.code.service

import com.example.moiza.domain.code.domain.repository.CodeRepository
import com.example.moiza.domain.code.presentation.dto.CodeResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetCodesService(
    private val codeRepository: CodeRepository,
) {
    @Transactional(readOnly = true)
    fun execute(keyword: String?): List<CodeResponse> {
        val codes = keyword?.let {
            codeRepository.findByKeywordContaining(keyword)
        } ?: codeRepository.findAll()

        return codes.map {
            CodeResponse(it.id, it.keyword)
        }
    }
}
