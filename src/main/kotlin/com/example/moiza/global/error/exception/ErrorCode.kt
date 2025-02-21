package com.example.moiza.global.error.exception

enum class ErrorCode(
    val status: Int,
    val message: String
) {
    EXPIRED_JWT(401, "Expired Jwt"),
    INVALID_JWT(401, "Invalid Jwt"),
    USER_NOT_FOUND(404, "User Not Found"),
    TOKEN_NOT_FOUND(404, "Token Not Found"),

    // portfolio
    PORTFOLIO_NOT_FOUND(404, "Portfolio Not Found"),

    // like
    ALREADY_LIKE(400, "Already Liked"),
    ALREADY_UNLIKE(400, "Already UnLiked"),

    // code
    CODE_NOT_FOUND(404, "Code Not Found"),
}
