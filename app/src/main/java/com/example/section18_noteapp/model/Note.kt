package com.example.section18_noteapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "notes_tbl")
data class Note(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo
    val title: String,

    @ColumnInfo
    val note: String,

    @ColumnInfo
    val entryDate: Date = Date.from(Instant.now())
)