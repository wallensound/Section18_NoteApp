package com.example.section18_noteapp.model

import java.time.LocalDateTime
import java.util.*

data class Note(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val note: String,
    val entryDate: LocalDateTime = LocalDateTime.now()
)