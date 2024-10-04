package com.example.moiza.domain.user.domain

import com.example.moiza.domain.user.domain.type.Authority
import com.example.moiza.domain.user.domain.type.School
import jakarta.persistence.*

@Entity
class User(
    email: String,
    nickname: String,
    profile: String,
    school: School,
) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(columnDefinition = "VARCHAR(40)", nullable = false, unique = true)
    var email: String = email
        protected set

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    var nickname: String = nickname
        protected set

    @Column(nullable = false)
    var profile: String = profile
        protected set

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var school: School = school
        protected set

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var authority: Authority = Authority.USER
        protected set

    fun isAdmin(): Boolean =
        this.authority == Authority.ADMIN
}
