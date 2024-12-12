package com.example.moiza.domain.portfolio.domain.type

enum class UserStatus(val level: Int) {
    NOT_LOGGED_IN(0),
    LOGGED_IN(1),
    PORTFOLIO_COMPLETED(2),
    PORTFOLIO_PUBLISHED(3),
}