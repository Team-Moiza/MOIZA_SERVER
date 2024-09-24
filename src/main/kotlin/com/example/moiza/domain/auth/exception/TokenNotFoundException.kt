package com.example.moiza.domain.auth.exception

import com.example.moiza.global.error.exception.BusinessException
import com.example.moiza.global.error.exception.ErrorCode

object TokenNotFoundException: BusinessException(ErrorCode.TOKEN_NOT_FOUND)