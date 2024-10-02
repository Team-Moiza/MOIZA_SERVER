package com.example.moiza.domain.community.poll.exception

import com.example.moiza.global.error.exception.BusinessException
import com.example.moiza.global.error.exception.ErrorCode

object PollOptionNotFoundException: BusinessException(ErrorCode.POLL_OPTION_NOT_FOUND)
