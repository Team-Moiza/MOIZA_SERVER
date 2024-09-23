package com.example.moiza.domain.auth.exception

import com.example.moiza.global.error.exception.BusinessException
import com.example.moiza.global.error.exception.ErrorCode

object UserNotFoundException: BusinessException(ErrorCode.USER_NOT_FOUND)