package com.example.moiza.domain.community.poll.domain

import jakarta.persistence.*
import java.time.LocalDate

@Entity(name = "poll")
class Poll(
    title: String,
    content: String,
    options: List<PollOption>,
    createdAt: LocalDate
) {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "VARCHAR")
    val id: Long = 0L

    @Column(name = "title", columnDefinition = "VARCHAR(100)", nullable = false)
    var title: String = title
        protected set

    @Column(name = "content", columnDefinition = "VARCHAR", nullable = false)
    var content: String = content
        protected set

    @OneToMany(mappedBy = "poll", cascade = [CascadeType.ALL])
    var options: List<PollOption> = options
        protected set

    @Column(name = "createdAt", columnDefinition = "DATE", nullable = false)
    var createdAt: LocalDate = createdAt
}
