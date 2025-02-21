package com.example.moiza.domain.like.domain

import com.example.moiza.domain.portfolio.domain.Portfolio
import com.example.moiza.domain.user.domain.User
import jakarta.persistence.*

@Entity
@Table(name = "likes")
class Like(
    portfolio: Portfolio,
    user: User,
) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id")
    var portfolio: Portfolio = portfolio
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User = user
        protected set
}