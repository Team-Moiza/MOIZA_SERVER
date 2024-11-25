package com.example.moiza.domain.portfolio.domain

import com.example.moiza.domain.user.domain.User
import jakarta.persistence.*

@Entity
class Portfolio(
    user: User,
    title: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User = user
        protected set

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    var title: String = title
        protected set

    @OneToMany(mappedBy = "portfolio", cascade = [CascadeType.ALL], orphanRemoval = true)
    val projects: MutableList<Project> = mutableListOf()

    @OneToMany(mappedBy = "portfolio", cascade = [CascadeType.ALL], orphanRemoval = true)
    val qualifications: MutableList<Qualification> = mutableListOf()

    @OneToMany(mappedBy = "portfolio", cascade = [CascadeType.ALL], orphanRemoval = true)
    val awards: MutableList<Award> = mutableListOf()

    @OneToMany(mappedBy = "portfolio", cascade = [CascadeType.ALL], orphanRemoval = true)
    val links: MutableList<Link> = mutableListOf()
}
