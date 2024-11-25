package com.example.moiza.domain.portfolio.domain

import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Qualification(
    name: String,
    score: String,
    date: LocalDate,
    portfolio: Portfolio
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(nullable = false)
    var name: String = name
        protected set

    @Column(nullable = false)
    var score: String = score
        protected set

    @Column(nullable = false)
    var date: LocalDate = date
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id")
    var portfolio: Portfolio = portfolio
        protected set
}
