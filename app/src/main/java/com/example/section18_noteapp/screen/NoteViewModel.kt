package com.example.section18_noteapp.screen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.section18_noteapp.model.Note
import com.example.section18_noteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()
    private val title = mutableStateOf("")
    private val note = mutableStateOf("")

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getNotes().distinctUntilChanged()
                .collect {
                    if (it.isEmpty()) {
                        Log.d("Empty", ": EmptyList")
                    }else {
                        _noteList.value = it
                    }
                }
        }
    }

    fun addNote(note: Note) = viewModelScope.launch { repository.insert(note = note) }
    fun updateNote(note: Note) = viewModelScope.launch { repository.update(note = note) }
    fun removeNote(note: Note) = viewModelScope.launch { repository.delete(note = note) }

    fun title(): MutableState<String> {
        return title
    }

    fun note(): MutableState<String> {
        return note
    }
}