package com.example.moiza.global.error.exception

abstract class BusinessException(
    val errorCode: ErrorCode
): RuntimeException()