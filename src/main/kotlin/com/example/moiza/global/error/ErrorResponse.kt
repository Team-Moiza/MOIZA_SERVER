package com.example.moiza.global.error

class ErrorResponse(
    private val status: Int,
    private val message: String
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