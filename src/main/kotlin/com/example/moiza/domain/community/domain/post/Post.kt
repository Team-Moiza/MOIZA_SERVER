package com.example.moiza.domain.community.domain.post

import com.example.moiza.domain.community.domain.Tag
import com.example.moiza.domain.community.domain.type.CommunityType
import com.example.moiza.domain.user.domain.User
import jakarta.persistence.*
import java.time.LocalDate

@Entity(name = "post")
class Post(
    id: Long,
    title: String,
    content: String,
    user: User,
    type: CommunityType,
    image: List<String>?,
    tag: List<Tag>?,
    createdAt: LocalDate,
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false, nullable = false)
    var user: User = user
        protected set

    @Column(name = "type", columnDefinition = "ENUM", nullable = true)
    var type: CommunityType = type
        protected set

    @ElementCollection
    var image: List<String>? = image
        protected set

    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], orphanRemoval = true)
    var tag: List<Tag> = tag ?: emptyList()

    @Column(name = "createdAt", columnDefinition = "DATE", nullable = false)
    var createdAt: LocalDate = createdAt
}
