package com.example.section18_noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.section18_noteapp.model.Note
import com.example.section18_noteapp.screen.NoteScreen
import com.example.section18_noteapp.screen.NoteViewModel
import com.example.section18_noteapp.ui.theme.Section18_NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Section18_NoteAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    NotesApp()
                }
            }
        }
    }
}

@Composable
fun NotesApp(noteViewModel: NoteViewModel = viewModel()) {
    val notes = noteViewModel.noteList.collectAsState().value
    val title = noteViewModel.title()
    val note = noteViewModel.note()

    NoteScreen(
        notes = notes,
        title = title,
        note = note,
        onAddNote = { noteViewModel.addNote(it) },
        onRemoveNote = { noteViewModel.removeNote(it) })
}