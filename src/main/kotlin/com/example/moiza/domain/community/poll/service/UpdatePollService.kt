package com.example.moiza.domain.community.poll.service

import com.example.moiza.domain.community.poll.domain.PollRepository
import com.example.moiza.domain.community.poll.exception.PollNotFoundException
import com.example.moiza.domain.community.poll.presentation.dto.res.PollIdResponse
import com.example.moiza.domain.user.facade.UserFacade
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Transactional
@Service
class UpdatePollService(
    private val pollRepository: PollRepository,
    private val userFacade: UserFacade
) {

    fun execute(title: String, content: String, pollId: Long): PollIdResponse {
        val user = userFacade.getCurrentUser()
        val poll = pollRepository.findByIdAndUser(pollId, user)
            ?: throw PollNotFoundException

        poll.update(title, content)

        val updated = pollRepository.save(poll)

        return PollIdResponse(updated.id)
    }
}
