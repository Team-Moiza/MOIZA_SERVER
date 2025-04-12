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
    MISSING_PROFILE_FOR_PORTFOLIO(409, "Missing profile information for portfolio registration"),
    MISSING_PIN_FOR_24_HOURS(422, "끌올은 하루에 한 번만 가능합니다. 24시간이 지나지 않았습니다."),

    // like
    ALREADY_LIKE(409, "Already Liked"),
    ALREADY_UNLIKE(409, "Already UnLiked"),

    // code
    CODE_NOT_FOUND(404, "Code Not Found"),

    // server
    VALIDATION_ERROR(400, "Validation Error"),
    NOT_SUPPORTED_URI_ERROR(500, "URI Not Supported"),
    NOT_SUPPORTED_METHOD_ERROR(405, "Method Not Allowed"),
}
