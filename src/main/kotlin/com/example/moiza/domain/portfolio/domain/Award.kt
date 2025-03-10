package com.example.moiza.domain.portfolio.domain

import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Award(
    name: String,
    type: String,
    date: LocalDate,
    description: String,
    competitionName: String,
    portfolio: Portfolio
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(nullable = false)
    var name: String = name
        protected set

    @Column(nullable = false)
    var type: String = type
        protected set

    @Column(nullable = false)
    var date: LocalDate = date
        protected set

    @Column(nullable = false, columnDefinition = "TEXT")
    var description: String = description
        protected set

    @Column(nullable = false, columnDefinition = "TEXT")
    var competitionName: String = competitionName
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id")
    var portfolio: Portfolio = portfolio
        protected set

    fun update(name: String, type: String, date: LocalDate, description: String, competitionName: String) {
        this.name = name
        this.type = type
        this.date = date
        this.description = description
        this.competitionName = competitionName
    }
}
