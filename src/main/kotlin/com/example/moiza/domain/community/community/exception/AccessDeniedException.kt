package com.example.moiza.domain.community.community.exception

import com.example.moiza.global.error.exception.BusinessException
import com.example.moiza.global.error.exception.ErrorCode

object AccessDeniedException: BusinessException(ErrorCode.ACCESS_DENIED)
