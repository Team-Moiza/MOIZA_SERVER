package com.example.moiza.domain.code.presentation

import com.example.moiza.domain.code.presentation.dto.CodeResponse
import com.example.moiza.domain.code.service.CreateCodeService
import com.example.moiza.domain.code.service.GetCodesService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/codes")
class CodeController(
    private val getCodesService: GetCodesService,
    private val createCodeService: CreateCodeService,
) {
    @GetMapping
    fun getCodes(
        @RequestParam(required = false) keyword: String?
    ): List<CodeResponse> = getCodesService.execute(keyword)

    @PostMapping
    fun createCode(@RequestParam keyword: String): CodeResponse = createCodeService.execute(keyword)
}
