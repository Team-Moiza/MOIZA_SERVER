package com.example.moiza.domain.portfolio.domain

import jakarta.persistence.*

@Entity
class Link(
    url: String,
    portfolio: Portfolio
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(nullable = false)
    var url: String = url
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id")
    var portfolio: Portfolio = portfolio
        protected set
}
