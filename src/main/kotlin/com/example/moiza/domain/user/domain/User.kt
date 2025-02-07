package com.example.moiza.domain.user.domain

import com.example.moiza.domain.portfolio.domain.type.UserStatus
import com.example.moiza.domain.user.domain.type.Authority
import com.example.moiza.domain.user.domain.type.EducationStatus
import com.example.moiza.domain.user.domain.type.Major
import com.example.moiza.domain.user.domain.type.School
import jakarta.persistence.*

@Entity
class User(
    email: String,
    nickname: String,
    profile: String,
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
    var authority: Authority = Authority.USER
        protected set

    @Enumerated(EnumType.STRING)
    var school: School? = null
        protected set

    @Enumerated(EnumType.STRING)
    var major: Major? = null
        protected set

    @Column(name = "education_status")
    @Enumerated(EnumType.STRING)
    var educationStatus: EducationStatus? = null
        protected set

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var userStatus: UserStatus = UserStatus.LOGGED_IN

    var introduce: String? = null
        protected set

    fun isAdmin(): Boolean =
        this.authority == Authority.ADMIN

    fun update(school: School, major: Major, educationStatus: EducationStatus, introduce: String) {
        this.school = school
        this.major = major
        this.educationStatus = educationStatus
        this.introduce = introduce
    }

    fun updateUserStatus(userStatus: UserStatus){
        this.userStatus = userStatus
    }
}
