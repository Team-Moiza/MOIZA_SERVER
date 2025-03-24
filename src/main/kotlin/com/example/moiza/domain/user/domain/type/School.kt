package com.example.moiza.domain.user.domain.type

enum class School(val schoolName: String) {
    BSSM("부산소프트웨어마이스터고등학교"),
    DGSM("대구소프트웨어마이스터고등학교"),
    DSM("대덕소프트웨어마이스터고등학교"),
    GSM("광주소프트웨어마이스터고등학교");

    fun getName(): String {
        return schoolName
    }
}