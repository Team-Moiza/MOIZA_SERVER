package com.example.moiza.domain.community.poll.service

import com.example.moiza.domain.community.poll.domain.entity.Poll
import com.example.moiza.domain.community.poll.domain.entity.PollOption
import com.example.moiza.domain.community.poll.domain.repository.PollRepository
import com.example.moiza.domain.community.poll.presentation.dto.req.CreatePollRequest
import com.example.moiza.domain.community.poll.presentation.dto.res.PollIdResponse
import com.example.moiza.domain.user.facade.UserFacade
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class CreatePollService(
    private val pollRepository: PollRepository,
    private val userFacade: UserFacade
) {

    fun execute(request: CreatePollRequest): PollIdResponse {
        val user = userFacade.getCurrentUser()

        val poll = Poll(
            title = request.title,
            content = request.content,
            user = user
        )

        val pollOptions = request.options.map { optionRequest ->
            PollOption(optionRequest.description, poll)
        }

        poll.addOptions(pollOptions)

        pollRepository.save(poll)
        
        return PollIdResponse(poll.id)
    }
}
