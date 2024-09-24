package com.example.moiza.global.error

class ErrorResponse(
    val status: Int,
    val message: String
) {
    private val errorLogsFormat: String = """
        {
            "status": "%s",
            "message": "%s"
        }
        """

    override fun toString(): String {
        return errorLogsFormat.format(
            status,
            message
        )
    }
}