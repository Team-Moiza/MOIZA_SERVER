package com.example.moiza.domain.user.domain

import com.example.moiza.domain.user.domain.type.Authority
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class User(
    email: String,
    nickname: String,
    authority: Authority
) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    var email: String = email
        protected set

    var nickname: String = nickname
        protected set

    var authority: Authority = authority
        protected set
}