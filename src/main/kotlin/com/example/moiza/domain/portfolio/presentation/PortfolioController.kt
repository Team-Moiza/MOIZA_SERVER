package com.example.moiza.domain.portfolio.presentation

import com.example.moiza.domain.portfolio.presentation.dto.req.CreatePortfolioRequest
import com.example.moiza.domain.portfolio.service.CreatePortfolioService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RequestMapping("/portfolio")
@RestController
class PortfolioController(
    private val createPortfolioService: CreatePortfolioService
) {

    @PostMapping
    fun createPortfolio(@Valid @RequestBody request: CreatePortfolioRequest)
        = createPortfolioService.execute(request)
}
