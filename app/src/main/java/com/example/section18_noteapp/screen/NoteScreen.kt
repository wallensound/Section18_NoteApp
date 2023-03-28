package com.example.section18_noteapp.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.section18_noteapp.components.NoteTextFields
import com.example.section18_noteapp.components.NoteCard
import com.example.section18_noteapp.model.Note
import java.util.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    title: MutableState<String>,
    note: MutableState<String>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Scaffold(topBar = {
            TopAppBar() {
                Text(
                    text = "JetNoteApp-Test",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }) { it ->
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxWidth()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    NoteTextFields(title = title, note = note, context = context) {
                        onAddNote(it)
                    }
                    Button(shape = CircleShape, onClick = {
                        if (title.value.isNotEmpty() && note.value.isNotEmpty()) {
                            onAddNote(Note(title = title.value, note = note.value))
                            title.value = ""
                            note.value = ""
                            Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            Toast.makeText(context, "Please add title and note", Toast.LENGTH_SHORT).show()
                        }
                    }) {
                        Text(text = "Save")
                    }
                    Divider(thickness = 2.dp)
                }
                LazyColumn(modifier = Modifier.fillMaxWidth(), reverseLayout = true) {
                    items(notes) { note ->
                        NoteCard(note = note) { notea ->
                            onRemoveNote(notea)
                        }
                    }
                }
            }
        }
    }
}