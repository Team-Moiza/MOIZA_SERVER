package com.example.moiza.domain.community.post.exception

import com.example.moiza.global.error.exception.BusinessException
import com.example.moiza.global.error.exception.ErrorCode

object PostNotFoundException: BusinessException(ErrorCode.POST_NOT_FOUND)