package com.example.moiza.domain.portfolio.presentation

import com.example.moiza.domain.portfolio.presentation.dto.req.PortfolioRequest
import com.example.moiza.domain.portfolio.service.*
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RequestMapping("/portfolio")
@RestController
class PortfolioController(
    private val createPortfolioService: CreatePortfolioService,
    private val updatePortfolioService: UpdatePortfolioService,
    private val deletePortfolioService: DeletePortfolioService,
    private val queryPortfolioListService: QueryPortfolioListService,
    private val queryPortfolioDetailService: QueryPortfolioDetailService,
    private val changePublishService: ChangePublishService,
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

    @GetMapping
    fun queryPortfolioList(pageable: Pageable)
        = queryPortfolioListService.execute(pageable)

    @GetMapping("/{portfolio-id}")
    fun queryPortfolioDetail(@PathVariable("portfolio-id") portfolioId: Long)
        = queryPortfolioDetailService.execute(portfolioId)

    @PatchMapping("/publish/{id}")
    fun changePublish(@PathVariable id: String)
        = changePublishService.execute(id)
}
