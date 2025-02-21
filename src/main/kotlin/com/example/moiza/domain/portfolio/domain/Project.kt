package com.example.moiza.domain.portfolio.domain

import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Project(
    title: String,
    status: Boolean,
    startDate: LocalDate,
    endDate: LocalDate?,
    description: String,
    link: String,
    portfolio: Portfolio
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    var title: String = title
        protected set

    @Column(nullable = false)
    var startDate: LocalDate = startDate
        protected set

    @Column(nullable = false)
    var endDate: LocalDate? = endDate
        protected set

    @Column(columnDefinition = "BOOLEAN", nullable = false)
    var status: Boolean = status
        protected set

    @Column(columnDefinition = "TEXT", nullable = false)
    var description: String = description
        protected set

    @Column(columnDefinition = "VARCHAR(255)", nullable = true)
    var link: String = link
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id")
    var portfolio: Portfolio = portfolio
        protected set

    fun update(title: String, startDate: LocalDate, endDate: LocalDate?, status: Boolean, description: String, link: String) {
        this.title = title
        this.startDate = startDate
        this.endDate = endDate
        this.status = status
        this.description = description
        this.link = link
    }
}
