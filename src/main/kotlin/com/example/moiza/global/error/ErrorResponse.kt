package com.example.moiza.global.error

class ErrorResponse(
    val status: Int,
    val message: String
) {
    override fun toString(): String {
        return ERROR_LOGS_FORMAT.format(
            status,
            message
        )
    }

    companion object {
        var ERROR_LOGS_FORMAT = """
            {
                "status": %s,
                "message": "%s"
            }
        """.trimIndent()
    }
}