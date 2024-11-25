package com.example.moiza.domain.portfolio.domain

import jakarta.persistence.*

@Entity
class Project(
    description: String,
    link: String,
    status: Boolean,
    period: String,
    portfolio: Portfolio
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(columnDefinition = "TEXT", nullable = false)
    var description: String = description
        protected set

    @Column(columnDefinition = "VARCHAR(255)", nullable = true)
    var link: String = link
        protected set

    @Column(columnDefinition = "BOOLEAN", nullable = false)
    var status: Boolean = status
        protected set

    @Column(nullable = false)
    var period: String = period
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id")
    var portfolio: Portfolio = portfolio
        protected set
}
