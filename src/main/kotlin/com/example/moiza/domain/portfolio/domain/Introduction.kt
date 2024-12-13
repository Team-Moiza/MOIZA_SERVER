package com.example.moiza.domain.portfolio.domain

import jakarta.persistence.*

@Entity
class Introduction(
    introduce: String,
    url: String,
    portfolio: Portfolio,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(nullable = false)
    var introduce: String = introduce
        protected set

    @Column(nullable = false)
    var url: String = url
        protected set

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false)
    var portfolio: Portfolio = portfolio
        protected set

    fun update(introduce: String, url: String) {
        this.introduce = introduce
        this.url = url
    }
}
