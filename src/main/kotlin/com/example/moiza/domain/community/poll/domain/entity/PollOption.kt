package com.example.moiza.domain.community.poll.domain.entity

import com.example.moiza.domain.community.vote.domain.Vote
import jakarta.persistence.*

@Entity(name = "poll_option")
class PollOption(
    description: String,
    poll: Poll
) {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    val id: Long = 0L

    @Column(name = "description", columnDefinition = "VARCHAR(100)", nullable = false)
    var description: String = description
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id", nullable = false)
    var poll: Poll = poll
        protected set

    @OneToMany(mappedBy = "pollOption", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var votes: MutableList<Vote> = mutableListOf()
        protected set
}
