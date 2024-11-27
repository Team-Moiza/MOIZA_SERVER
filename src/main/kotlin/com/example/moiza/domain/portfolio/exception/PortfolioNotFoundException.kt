package com.example.moiza.domain.portfolio.exception

import com.example.moiza.global.error.exception.BusinessException
import com.example.moiza.global.error.exception.ErrorCode

object PortfolioNotFoundException: BusinessException(ErrorCode.PORTFOLIO_NOT_FOUND)
