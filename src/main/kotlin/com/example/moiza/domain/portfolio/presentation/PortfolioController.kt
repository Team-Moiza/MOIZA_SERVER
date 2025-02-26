package com.example.moiza.domain.portfolio.presentation

import com.example.moiza.domain.portfolio.presentation.dto.PortfolioFilter
import com.example.moiza.domain.portfolio.presentation.dto.req.PortfolioRequest
import com.example.moiza.domain.portfolio.presentation.dto.req.UpdatePortfolioRequest
import com.example.moiza.domain.portfolio.service.*
import com.example.moiza.domain.user.domain.type.School
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RequestMapping("/portfolios")
@RestController
class PortfolioController(
    private val createPortfolioService: CreatePortfolioService,
    private val updatePortfolioService: UpdatePortfolioService,
    private val deletePortfolioService: DeletePortfolioService,
    private val queryPortfolioListService: QueryPortfolioListService,
    private val queryPortfolioDetailService: QueryPortfolioDetailService,
    private val changePublishService: ChangePublishService,
    private val queryMyPortfolioService: QueryMyPortfolioService,
) {

    @PostMapping
    fun createPortfolio(@Valid @RequestBody request: PortfolioRequest) = createPortfolioService.execute(request)

    @PatchMapping("/{portfolio-id}")
    fun updatePortfolio(
        @PathVariable("portfolio-id") portfolioId: Long,
        @Valid @RequestBody request: UpdatePortfolioRequest
    ) = updatePortfolioService.execute(portfolioId, request)

    @DeleteMapping("/{portfolio-id}")
    fun deletePortfolio(@PathVariable("portfolio-id") portfolioId: Long) = deletePortfolioService.execute(portfolioId)

    @GetMapping
    fun queryPortfolioList(
        pageable: Pageable,
        @RequestParam(required = false) code: List<Long>?,
        @RequestParam(required = false) school: School?,
        @RequestParam(required = false) isEmployed: Boolean?,
    ) = queryPortfolioListService.execute(pageable, PortfolioFilter(code, school, isEmployed))

    @GetMapping("/{portfolio-id}")
    fun queryPortfolioDetail(@PathVariable("portfolio-id") portfolioId: Long) =
        queryPortfolioDetailService.execute(portfolioId)

    @PatchMapping("/publish/{portfolio-id}")
    fun changePublish(@PathVariable("portfolio-id") portfolioId: Long) = changePublishService.execute(portfolioId)

    @GetMapping("/my")
    fun queryMyPortfolio() = queryMyPortfolioService.execute()
}
