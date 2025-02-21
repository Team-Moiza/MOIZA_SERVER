package com.example.moiza.global.error

import com.example.moiza.global.error.exception.BusinessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(e: BusinessException): ResponseEntity<ErrorResponse> {
        val code = e.errorCode
        return ResponseEntity(
            ErrorResponse(code.status, code.message),
            HttpStatus.valueOf(code.status)
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleBadRequestExceptions(e: MethodArgumentNotValidException): ResponseEntity<Map<String, Map<String, String>>> {
        val errors = e.bindingResult.fieldErrors
            .associateBy({ it.field }, { it.defaultMessage ?: "Invalid value" })

        return ResponseEntity(getErrorsMap(errors), HttpStatus.BAD_REQUEST)
    }

    private fun getErrorsMap(errors: Map<String, String>): Map<String, Map<String, String>> {
        return mapOf("errors" to errors)
    }
}