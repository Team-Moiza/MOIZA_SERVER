package com.example.moiza.domain.code.exception

import com.example.moiza.global.error.exception.BusinessException
import com.example.moiza.global.error.exception.ErrorCode

object CodeNotFoundException: BusinessException(ErrorCode.CODE_NOT_FOUND)
