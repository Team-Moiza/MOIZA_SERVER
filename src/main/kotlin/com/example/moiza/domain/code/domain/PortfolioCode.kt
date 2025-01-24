package com.example.moiza.domain.code.domain

import com.example.moiza.domain.portfolio.domain.Portfolio
import jakarta.persistence.*

@Entity
class PortfolioCode(
    portfolio: Portfolio,
    code: Code
) {
    @EmbeddedId
    var id: PortfolioCodeId = PortfolioCodeId(portfolio.id, code.id)
        protected set

    @MapsId("portfolioId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false)
    var portfolio: Portfolio = portfolio
        protected set

    @MapsId("codeId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_id", nullable = false)
    var code: Code = code
        protected set
}
