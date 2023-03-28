package com.example.section18_noteapp.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.section18_noteapp.model.Note

class NoteViewModel : ViewModel() {
    private val noteList = mutableStateListOf<Note>()
    private val title = mutableStateOf("")
    private val note = mutableStateOf("")

    fun addNote(note: Note) {
        noteList.add(note)
    }

    fun removeNote(note: Note) {
        noteList.remove(note)
    }

    fun getNotes(): List<Note> {
        return noteList
    }

    fun title(): MutableState<String> {
        return title
    }

    fun note(): MutableState<String> {
        return note
    }
}