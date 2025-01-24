package com.example.moiza.domain.code.presentation

import com.example.moiza.domain.code.presentation.dto.CreateCodeResponse
import com.example.moiza.domain.code.service.CreateCodeService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/code")
class CodeController(
    private val createCodeService: CreateCodeService
) {
    @PostMapping
    fun createCode(keyword: String): CreateCodeResponse
        = createCodeService.execute(keyword)
}
