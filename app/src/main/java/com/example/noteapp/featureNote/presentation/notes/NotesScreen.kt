package com.example.noteapp.featureNote.presentation.notes

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NotesScreen(viewModel: NotesViewModel = hiltViewModel()) {
  val state = viewModel.state
  val scaffoldState = rememberScaffoldState()
  val scope = rememberCoroutineScope()
  
  Scaffold(scaffoldState = scaffoldState) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(
          16.dp
        )
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment
          .CenterVertically
      ) {
        Text(text = "You notes", style = MaterialTheme.typography.h4)
        IconButton(onClick = {
          viewModel.onEvent(NotesEvent.ToggleOrderSection) }) {
          Icon(imageVector = Icons.Default.Sort, contentDescription = "Sort")
        }
      }
    }
  }
}