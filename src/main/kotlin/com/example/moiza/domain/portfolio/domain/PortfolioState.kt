package com.example.moiza.domain.portfolio.domain

import com.example.moiza.domain.portfolio.domain.type.UserStatus
import jakarta.persistence.*

@Entity
class PortfolioState(
    portfolio: Portfolio,
    userStatus: UserStatus,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false)
    var portfolio: Portfolio = portfolio
        protected set

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    var userStatus: UserStatus = userStatus
        protected set
}