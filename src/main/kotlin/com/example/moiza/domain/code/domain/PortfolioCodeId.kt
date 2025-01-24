package com.example.moiza.domain.code.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import lombok.EqualsAndHashCode
import java.io.Serializable

@Embeddable
@EqualsAndHashCode
class PortfolioCodeId(
    portfolioId: Long,
    codeId: Long,
): Serializable {
    @Column(name = "portfolio_id")
    var portfolioId: Long = portfolioId
        protected set

    @Column(name = "code_id")
    var codeId: Long = codeId
        protected set
}
