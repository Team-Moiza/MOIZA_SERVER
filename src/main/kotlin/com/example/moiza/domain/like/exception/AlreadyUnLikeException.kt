package com.example.moiza.domain.like.exception
import com.example.moiza.global.error.exception.BusinessException
import com.example.moiza.global.error.exception.ErrorCode

object AlreadyUnLikeException: BusinessException(ErrorCode.ALREADY_UNLIKE)
