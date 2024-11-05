package com.example.moiza.domain.community.poll.exception

import com.example.moiza.global.error.exception.BusinessException
import com.example.moiza.global.error.exception.ErrorCode

object PollNotFoundException: BusinessException(ErrorCode.POLL_NOT_FOUND)