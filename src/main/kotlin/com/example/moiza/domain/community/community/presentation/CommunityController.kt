package com.example.moiza.domain.community.community.presentation

import com.example.moiza.domain.community.community.presentation.dto.req.UpdatePollRequest
import com.example.moiza.domain.community.community.presentation.dto.req.UpdatePostRequest
import com.example.moiza.domain.community.community.service.QueryAllContentsService
import com.example.moiza.domain.community.poll.presentation.dto.req.CreatePollRequest
import com.example.moiza.domain.community.poll.service.CreatePollService
import com.example.moiza.domain.community.poll.service.DeletePollService
import com.example.moiza.domain.community.poll.service.UpdatePollService
import com.example.moiza.domain.community.post.presentation.dto.req.CreatePostRequest
import com.example.moiza.domain.community.post.service.*
import com.example.moiza.domain.community.vote.service.AddVoteService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
@RequestMapping("/community")
class CommunityController(
    private val queryAllContentsService: QueryAllContentsService,
    private val createPostService: CreatePostService,
    private val deletePostService: DeletePostService,
    private val updatePostService: UpdatePostService,
    private val queryPostDetailService: QueryPostDetailService,
    private val createPollService: CreatePollService,
    private val deletePollService: DeletePollService,
    private val updatePollService: UpdatePollService,
    private val addVoteService: AddVoteService
) {

    @GetMapping
    fun queryAllContents() = queryAllContentsService.execute()

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    fun createPost(@RequestBody request: CreatePostRequest)
        = createPostService.execute(request)

    @DeleteMapping("/post/{post-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePost(@PathVariable("post-id") postId: Long)
        = deletePostService.execute(postId)

    @PatchMapping("/post/{post-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updatePost(
        @RequestBody request: UpdatePostRequest,
        @PathVariable("post-id") postId: Long
    ) = updatePostService.execute(request.title, request.content, postId)

    @GetMapping("/post/{post-id}")
    fun queryPostDetail(@PathVariable("post-id") postId: Long)
        = queryPostDetailService.execute(postId)

    @PostMapping("/poll")
    @ResponseStatus(HttpStatus.CREATED)
    fun createPoll(@RequestBody request: CreatePollRequest)
        = createPollService.execute(request)

    @DeleteMapping("/poll/{poll-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePoll(@PathVariable("poll-id") pollId: Long)
        = deletePollService.execute(pollId)

    @PatchMapping("/poll/{poll-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updatePoll(
        @RequestBody request: UpdatePollRequest,
        @PathVariable("poll-id") pollId: Long
    ) = updatePollService.execute(request.title, request.content, pollId)

    @PostMapping("/vote/{poll-option-id}")
    @ResponseStatus(HttpStatus.OK)
    fun addVote(@PathVariable("poll-option-id") pollOptionId: Long)
        = addVoteService.execute(pollOptionId)
}
