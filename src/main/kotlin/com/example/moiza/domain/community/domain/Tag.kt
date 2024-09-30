package com.example.moiza.domain.community.domain

import com.example.moiza.domain.community.domain.post.Post
import jakarta.persistence.*

@Entity(name = "tag")
class Tag(
    name: String,
    post: Post
) {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    val id: Long = 0L

    @Column(name = "name", columnDefinition = "VARCHAR(50)", nullable = false)
    var name: String = name
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    var post: Post = post
        protected set
}
