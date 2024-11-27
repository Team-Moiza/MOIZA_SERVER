package com.example.moiza.domain.portfolio.presentation

import com.example.moiza.domain.portfolio.presentation.dto.req.PortfolioRequest
import com.example.moiza.domain.portfolio.service.CreatePortfolioService
import com.example.moiza.domain.portfolio.service.DeletePortfolioService
import com.example.moiza.domain.portfolio.service.UpdatePortfolioService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RequestMapping("/portfolio")
@RestController
class PortfolioController(
    private val createPortfolioService: CreatePortfolioService,
    private val updatePortfolioService: UpdatePortfolioService,
    private val deletePortfolioService: DeletePortfolioService
) {

    @PostMapping
    fun createPortfolio(@Valid @RequestBody request: PortfolioRequest)
        = createPortfolioService.execute(request)

    @PatchMapping("/{portfolio-id}")
    fun updatePortfolio(
        @PathVariable("portfolio-id") portfolioId: Long,
        @Valid @RequestBody request: PortfolioRequest
    ) = updatePortfolioService.execute(portfolioId, request)

    @DeleteMapping("/{portfolio-id}")
    fun deletePortfolio(@PathVariable("portfolio-id") portfolioId: Long)
        = deletePortfolioService.execute(portfolioId)
}
