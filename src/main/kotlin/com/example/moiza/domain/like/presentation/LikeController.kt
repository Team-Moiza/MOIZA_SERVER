package com.example.moiza.domain.like.presentation

import com.example.moiza.domain.like.service.LikeService
import com.example.moiza.domain.like.service.ListLikesService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/likes")
@RestController
class LikeController(
    private val listLikesService: ListLikesService,
    private val likeService: LikeService,
) {
    @GetMapping
    fun findAll() = listLikesService.execute()

    @PostMapping
    fun like(
        @RequestParam portfolioId: Long,
    ) = likeService.execute(portfolioId)

}