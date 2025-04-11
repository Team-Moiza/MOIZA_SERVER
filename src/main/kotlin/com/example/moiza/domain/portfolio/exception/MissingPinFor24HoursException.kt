package com.example.moiza.domain.portfolio.exception

import com.example.moiza.global.error.exception.BusinessException
import com.example.moiza.global.error.exception.ErrorCode

object MissingPinFor24HoursException: BusinessException(ErrorCode.MISSING_PIN_FOR_24_HOURS)
