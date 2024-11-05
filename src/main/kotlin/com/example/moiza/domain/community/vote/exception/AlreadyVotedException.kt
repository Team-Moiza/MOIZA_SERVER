package com.example.moiza.domain.community.vote.exception

import com.example.moiza.global.error.exception.BusinessException
import com.example.moiza.global.error.exception.ErrorCode

object AlreadyVotedException: BusinessException(ErrorCode.ALREADY_VOTED)
