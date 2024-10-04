package com.example.moiza.domain

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseTimeEntity(

    @Column(nullable = false, updatable = false, columnDefinition = "DATETIME(6)")
    val createdAt: LocalDateTime = LocalDateTime.now()
)
