package com.example.moiza.domain.like.service

import com.example.moiza.domain.like.domain.repository.LikeRepository
import com.example.moiza.domain.like.presentation.dto.response.LikesResponse
import com.example.moiza.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ListLikesService(
    private val userFacade: UserFacade,
    private val likeRepository: LikeRepository,
) {
    @Transactional(readOnly = true)
    fun execute(): List<LikesResponse> {
        val user = userFacade.getCurrentUser()
        val likes = likeRepository.findAllByUser(user)

        return likes.map { LikesResponse.from(it) }
    }
}