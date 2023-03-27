package com.example.section18_noteapp.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NoteScreen() {
    val title = remember {
        mutableStateOf("")
    }
    val note = remember {
        mutableStateOf("")
    }
    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(5.dp)) {
        Scaffold(topBar = {TopAppBar(backgroundColor = Color.LightGray) {
            Text(text = "JetNoteApp-Test", style = MaterialTheme.typography.h6, modifier = Modifier.padding(10.dp))
        }}) { it ->
            Column(modifier = Modifier
                .padding(it)
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                TextField(colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next), label = { Text(text = "Title")}, singleLine = true, value = title.value, onValueChange = { newString ->
                    title.value = newString
                })
                TextField(colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done), label = { Text(text = "Add note")}, singleLine = true, value = note.value, onValueChange = { newString ->
                    note.value = newString
                })
                Button(shape = CircleShape, onClick = { /*TODO*/ }) {
                    Text(text = "Save")
                }
                Divider(thickness = 2.dp)
            }
        }
    }
}