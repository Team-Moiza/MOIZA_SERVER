package com.example.moiza.domain.community.community.domain.entity

import com.example.moiza.domain.community.community.domain.entity.type.CommunityType
import com.example.moiza.domain.user.domain.User
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Column
import jakarta.persistence.ManyToOne
import jakarta.persistence.Enumerated
import jakarta.persistence.EnumType
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.MappedSuperclass
import java.time.LocalDate

@MappedSuperclass
abstract class Community(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    val id: Long = 0L,

    @Column(name = "title", columnDefinition = "VARCHAR(100)", nullable = false)
    var title: String,

    @Column(name = "content", columnDefinition = "VARCHAR(1000)", nullable = false)
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "VARCHAR(4)", nullable = false)
    var type: CommunityType,

    @Column(name = "createdAt", columnDefinition = "DATE", nullable = false)
    var createdAt: LocalDate
) {

    fun update(title: String, content: String) {
        this.title = title
        this.content = content
    }
}
