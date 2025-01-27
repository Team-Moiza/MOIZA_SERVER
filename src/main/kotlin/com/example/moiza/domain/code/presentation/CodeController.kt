package com.example.moiza.domain.code.presentation

import com.example.moiza.domain.code.presentation.dto.CreateCodeResponse
import com.example.moiza.domain.code.presentation.dto.QueryCodeKeywordResponse
import com.example.moiza.domain.code.service.CreateCodeService
import com.example.moiza.domain.code.service.QueryCodeKeywordService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/code")
class CodeController(
    private val createCodeService: CreateCodeService,
    private val queryCodeKeywordService: QueryCodeKeywordService,
) {
    @PostMapping
    fun createCode(keyword: String): CreateCodeResponse
        = createCodeService.execute(keyword)

    @GetMapping
    fun queryCodeKeyword(@RequestParam("code-id") codeId: Long): QueryCodeKeywordResponse
        = queryCodeKeywordService.execute(codeId)
}
