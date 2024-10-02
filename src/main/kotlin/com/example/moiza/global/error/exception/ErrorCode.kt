package com.example.moiza.global.error.exception

enum class ErrorCode(
    val status: Int,
    val message: String
) {
    EXPIRED_JWT(401, "Expired Jwt"),
    INVALID_JWT(401, "Invalid Jwt"),
    USER_NOT_FOUND(404, "User Not Found"),
    TOKEN_NOT_FOUND(404, "Token Not Found"),
    NOT_SCHOOL_USER(400, "Not School User"),

    POST_NOT_FOUND(404, "Post Not Found"),

    POLL_NOT_FOUND(404, "Poll Not Found"),
    POLL_OPTION_NOT_FOUND(404, "Poll Option Not Found"),

    ALREADY_VOTED(409, "Already voted")
}
