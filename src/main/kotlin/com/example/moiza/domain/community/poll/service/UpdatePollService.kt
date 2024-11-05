package com.example.moiza.domain.community.poll.service

import com.example.moiza.domain.community.poll.domain.repository.PollRepository
import com.example.moiza.domain.community.poll.exception.PollNotFoundException
import com.example.moiza.domain.community.poll.presentation.dto.res.PollIdResponse
import com.example.moiza.domain.user.facade.UserFacade
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Transactional
@Service
class UpdatePollService(
    private val pollRepository: PollRepository,
    private val userFacade: UserFacade
) {

    fun execute(title: String, content: String, pollId: Long): PollIdResponse {
        val user = userFacade.getCurrentUser()
        val poll = pollRepository.findByIdOrNull(pollId) ?: throw PollNotFoundException

        poll.checkAuthority(user)
        poll.update(title, content)

        return PollIdResponse(poll.id)
    }
}
