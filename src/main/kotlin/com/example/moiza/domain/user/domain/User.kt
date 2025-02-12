package com.example.moiza.domain.user.domain

import com.example.moiza.domain.portfolio.domain.type.UserStatus
import com.example.moiza.domain.user.domain.type.*
import jakarta.persistence.*
import java.time.LocalDate

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
    var school: School? = null // 학교
        protected set

    @Enumerated(EnumType.STRING)
    var major: Major? = null // 학과
        protected set

    @Column(name = "education_status")
    @Enumerated(EnumType.STRING)
    var educationStatus: EducationStatus? = null // 재학 상태
        protected set

    @Column(name = "user_status", nullable = false)
    @Enumerated(EnumType.STRING)
    var userStatus: UserStatus = UserStatus.LOGGED_IN // 유저 상태

    @Column(length = 100)
    var introduce: String? = null
        protected set

    var enrollmentStartDate: LocalDate? = null // 입학일
        protected set

    var enrollmentEndDate: LocalDate? = null // 졸업일
        protected set

    var company: String? = null // 회사
        protected set

    @Enumerated(EnumType.STRING)
    var job: Job? = null // 개발 직무
        protected set

    fun update(nickname: String, school: School, major: Major, educationStatus: EducationStatus,
               enrollmentStartDate: LocalDate, enrollmentEndDate: LocalDate, job: Job,
               company: String, introduce: String) {
        this.nickname = nickname
        this.school = school
        this.major = major
        this.educationStatus = educationStatus
        this.enrollmentStartDate = enrollmentStartDate
        this.enrollmentEndDate = enrollmentEndDate
        this.job = job
        this.company = company
        this.introduce = introduce
    }

    fun updateUserStatus(userStatus: UserStatus) {
        this.userStatus = userStatus
    }
}
