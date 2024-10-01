package com.example.moiza.domain.community.poll.service

import com.example.moiza.domain.community.community.domain.type.CommunityType
import com.example.moiza.domain.community.poll.domain.Poll
import com.example.moiza.domain.community.poll.domain.PollOption
import com.example.moiza.domain.community.poll.domain.PollRepository
import com.example.moiza.domain.community.poll.presentation.dto.req.CreatePollRequest
import com.example.moiza.domain.community.poll.presentation.dto.res.CreatePollResponse
import com.example.moiza.domain.community.post.presentation.dto.res.CreatePostResponse
import com.example.moiza.domain.user.facade.UserFacade
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
@Transactional
class CreatePollService(
    private val pollRepository: PollRepository,
    private val userFacade: UserFacade
) {

    fun execute(request: CreatePollRequest): CreatePollResponse {
        val user = userFacade.getCurrentUser()

        val poll = Poll(
            title = request.title,
            content = request.content,
            user = user,
            type = CommunityType.POLL,
            createdAt = LocalDate.now()
        )

        val pollOptions = request.options.map { optionRequest ->
            PollOption(optionRequest.description, poll)
        }

        poll.addOptions(pollOptions)

        pollRepository.save(poll)
        
        return CreatePollResponse(poll.id)
    }
}
