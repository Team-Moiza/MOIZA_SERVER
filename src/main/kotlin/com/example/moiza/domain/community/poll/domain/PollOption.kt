package com.example.moiza.domain.community.poll.domain

import jakarta.persistence.*

@Entity(name = "poll_option")
class PollOption(
    pollId: Long,
    description: String,
) {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "VARCHAR")
    val id: Long = 0L

    @Column(name = "poll_id", nullable = false)
    var pollId: Long = pollId
        protected set

    @Column(name = "description", columnDefinition = "VARCHAR(255)", nullable = false)
    var description: String = description
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id", updatable = false)
    var poll: Poll? = null
}
