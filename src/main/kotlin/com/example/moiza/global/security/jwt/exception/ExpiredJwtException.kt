package com.teaminsert.homepage.global.security.jwt.exception

import com.example.moiza.global.error.exception.BusinessException
import com.example.moiza.global.error.exception.ErrorCode

object ExpiredJwtException: BusinessException(ErrorCode.EXPIRED_JWT)