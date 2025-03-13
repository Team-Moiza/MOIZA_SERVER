package com.example.moiza.global.error

import com.example.moiza.global.error.exception.BusinessException
import com.example.moiza.global.error.exception.ErrorCode
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.multipart.MultipartException
import org.springframework.web.multipart.support.MissingServletRequestPartException
import org.springframework.web.servlet.NoHandlerFoundException

@RestControllerAdvice
class GlobalExceptionHandler {
    companion object {
        private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)
    }

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
        val errors = e.bindingResult.fieldErrors.associate { it.field to (it.defaultMessage ?: "Invalid value") }
        return ResponseEntity(getErrorsMap(errors), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleHttpRequestMethodNotSupportedException(e: HttpRequestMethodNotSupportedException): ResponseEntity<ErrorResponse> {
        val errorCode = ErrorCode.NOT_SUPPORTED_METHOD_ERROR
        errorLog(errorCode, e.message ?: "")
        return ResponseEntity(
            ErrorResponse(errorCode.status, errorCode.message),
            HttpStatus.valueOf(errorCode.status)
        )
    }

    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleNoHandlerFoundException(e: NoHandlerFoundException): ResponseEntity<ErrorResponse> {
        val errorCode = ErrorCode.NOT_SUPPORTED_URI_ERROR
        errorLog(errorCode, e.message ?: "")
        return ResponseEntity(
            ErrorResponse(errorCode.status, errorCode.message),
            HttpStatus.valueOf(errorCode.status)
        )
    }

    @ExceptionHandler(HttpMessageNotReadableException::class, MismatchedInputException::class)
    fun handleHttpMessageNotReadableException(e: Exception): ResponseEntity<ErrorResponse> {
        val errorCode = ErrorCode.VALIDATION_ERROR
        errorLog(errorCode, e.message ?: "")
        return ResponseEntity(
            ErrorResponse(errorCode.status, errorCode.message),
            HttpStatus.valueOf(errorCode.status)
        )
    }

    @ExceptionHandler(
        UnsatisfiedServletRequestParameterException::class,
        MissingServletRequestPartException::class,
        MissingServletRequestParameterException::class,
        MultipartException::class
    )
    fun handleUnsatisfiedServletRequestParameterException(e: Exception): ResponseEntity<ErrorResponse> {
        val errorCode = ErrorCode.VALIDATION_ERROR
        errorLog(errorCode, e.message ?: "")
        return ResponseEntity(
            ErrorResponse(errorCode.status, errorCode.message),
            HttpStatus.valueOf(errorCode.status)
        )
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatchException(e: MethodArgumentTypeMismatchException): ResponseEntity<ErrorResponse> {
        val errorCode = ErrorCode.VALIDATION_ERROR
        errorLog(errorCode, e.message ?: "")
        return ResponseEntity(
            ErrorResponse(errorCode.status, errorCode.message),
            HttpStatus.valueOf(errorCode.status)
        )
    }

    @ExceptionHandler(BindException::class)
    fun handleBindException(e: BindException): ResponseEntity<ErrorResponse> {
        val errorCode = ErrorCode.VALIDATION_ERROR
        errorLog(errorCode, e.message)
        return ResponseEntity(
            ErrorResponse(errorCode.status, errorCode.message),
            HttpStatus.valueOf(errorCode.status)
        )
    }

    private fun getErrorsMap(errors: Map<String, String>): Map<String, Map<String, String>> {
        return mapOf("errors" to errors)
    }

    private fun errorLog(errorCode: ErrorCode, message: String) {
        log.error("errorCode : {}", errorCode)
        log.error("message : {}", message)
    }
}