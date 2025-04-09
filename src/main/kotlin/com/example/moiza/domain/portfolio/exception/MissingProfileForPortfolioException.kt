package com.example.moiza.domain.portfolio.exception

import com.example.moiza.global.error.exception.BusinessException
import com.example.moiza.global.error.exception.ErrorCode

object MissingProfileForPortfolioException: BusinessException(ErrorCode.MISSING_PROFILE_FOR_PORTFOLIO)
