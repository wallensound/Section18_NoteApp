package com.example.section18_noteapp.repository

import com.example.section18_noteapp.data.NoteDatabaseDao
import com.example.section18_noteapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {
    suspend fun insert(note: Note) = noteDatabaseDao.insert(note = note)
    suspend fun update(note: Note) = noteDatabaseDao.update(note = note)
    suspend fun delete(note: Note) = noteDatabaseDao.delete(note = note)
    suspend fun deleteAll() = noteDatabaseDao.deleteAll()
    suspend fun getNoteById(id: String): Note = noteDatabaseDao.getNoteById(id = id)
    fun getNotes(): Flow<List<Note>> = noteDatabaseDao.getNotes().flowOn(Dispatchers.IO).conflate()
}