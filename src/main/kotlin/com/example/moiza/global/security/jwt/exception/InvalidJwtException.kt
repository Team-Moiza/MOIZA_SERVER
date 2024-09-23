package com.teaminsert.homepage.global.security.jwt.exception

import com.example.moiza.global.error.exception.BusinessException
import com.example.moiza.global.error.exception.ErrorCode

object InvalidJwtException: BusinessException(ErrorCode.INVALID_JWT)