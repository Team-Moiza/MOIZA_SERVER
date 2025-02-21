package com.example.moiza.domain.like.presentation

import com.example.moiza.domain.like.service.LikeService
import com.example.moiza.domain.like.service.ListLikesService
import com.example.moiza.domain.like.service.UnLikeService
import org.springframework.web.bind.annotation.*

@RequestMapping("/likes")
@RestController
class LikeController(
    private val listLikesService: ListLikesService,
    private val likeService: LikeService,
    private val unLikeService: UnLikeService
) {
    @GetMapping
    fun findAll() = listLikesService.execute()

    @PostMapping
    fun like(
        @RequestParam portfolioId: Long,
    ) = likeService.execute(portfolioId)

    @PutMapping
    fun unLike(
        @RequestParam portfolioId: Long,
    ) = unLikeService.execute(portfolioId)
}