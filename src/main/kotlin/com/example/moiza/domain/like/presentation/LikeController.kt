package com.example.moiza.domain.like.presentation

import com.example.moiza.domain.like.domain.repository.LikeRepository
import com.example.moiza.domain.like.service.IsLikedService
import com.example.moiza.domain.like.service.LikeService
import com.example.moiza.domain.like.service.ListLikesService
import com.example.moiza.domain.like.service.UnLikeService
import org.springframework.web.bind.annotation.*

@RequestMapping("/likes")
@RestController
class LikeController(
    private val listLikesService: ListLikesService,
    private val isLikedService: IsLikedService,
    private val likeService: LikeService,
    private val unLikeService: UnLikeService
) {
    @GetMapping
    fun findAll() = listLikesService.execute()

    @GetMapping("/{portfolio-id}")
    fun isLiked(@PathVariable("portfolio-id") id: Long) = isLikedService.execute(id)

    @PostMapping
    fun like(
        @RequestParam portfolioId: Long,
    ) = likeService.execute(portfolioId)

    @PutMapping
    fun unLike(
        @RequestParam portfolioId: Long,
    ) = unLikeService.execute(portfolioId)
}