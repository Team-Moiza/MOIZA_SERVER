package com.example.moiza.domain.community.vote.service

import com.example.moiza.domain.community.poll.domain.repository.PollOptionRepository
import com.example.moiza.domain.community.poll.exception.PollOptionNotFoundException
import com.example.moiza.domain.community.vote.domain.Vote
import com.example.moiza.domain.community.vote.domain.VoteRepository
import com.example.moiza.domain.community.vote.exception.AlreadyVotedException
import com.example.moiza.domain.user.facade.UserFacade
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class AddVoteService(
    private val voteRepository: VoteRepository,
    private val pollOptionRepository: PollOptionRepository,
    private val userFacade: UserFacade
) {

    fun execute(pollOptionId: Long) {
        val user = userFacade.getCurrentUser()

        val pollOption = pollOptionRepository.findById(pollOptionId).orElseThrow {
            throw PollOptionNotFoundException
        }

        val existingVote = voteRepository.findByPollOptionAndUser(pollOption, user)
        if (existingVote != null) {
            throw AlreadyVotedException
        }

        val vote = Vote(pollOption, user)
        voteRepository.save(vote)
    }
}
