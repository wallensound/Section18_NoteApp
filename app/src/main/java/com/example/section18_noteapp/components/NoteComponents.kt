package com.example.section18_noteapp.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.bawp.jetnote.util.formatDate
import com.example.section18_noteapp.model.Note
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteTextFields(title: MutableState<String>, note: MutableState<String>, context: Context, onAddNote: (Note) -> Unit,) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    TextField(
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(onNext = {
            keyboardController?.hide()
            focusManager.moveFocus(FocusDirection.Down)

        }),
        label = { Text(text = "Title") },
        singleLine = true,
        value = title.value,
        onValueChange = { newString ->
            title.value = newString
        })
    TextField(
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
            if (title.value.isNotEmpty() && note.value.isNotEmpty()) {
                onAddNote(Note(title = title.value, note = note.value))
                title.value = ""
                note.value = ""
                Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(context, "Please add title and note", Toast.LENGTH_SHORT).show()
            }
        }),
        label = { Text(text = "Add note") },
        singleLine = true,
        value = note.value,
        onValueChange = { newString ->
            note.value = newString
        })
}
@Composable

fun NoteCard(note: Note, onClicked: (Note) -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp)
        .clip(RoundedCornerShape(topEnd = 33.dp)), elevation = 5.dp) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.padding(5.dp)) {
                Text(text = note.title, style = MaterialTheme.typography.h6)
                Text(text = note.note, style = MaterialTheme.typography.body1)
                Text(text = formatDate(note.entryDate.time), style = MaterialTheme.typography.caption)
            }
            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth().padding(end = 30.dp)) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Note", modifier = Modifier.clickable {
                    onClicked(note)
                })
            }
        }
    }
}