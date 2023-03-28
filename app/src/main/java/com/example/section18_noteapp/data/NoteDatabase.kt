package com.example.section18_noteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bawp.jetnote.util.DateConverter
import com.bawp.jetnote.util.UUIDConverter
import com.example.section18_noteapp.model.Note


@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDatabaseDao
}