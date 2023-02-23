package com.example.noteapp.featureNote.presentation.notes

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.noteapp.R
import com.example.noteapp.featureNote.domain.model.Note
import com.example.noteapp.featureNote.domain.util.NoteOrder
import com.example.noteapp.featureNote.presentation.notes.components.NoteItem
import com.example.noteapp.featureNote.presentation.notes.components.OrderSection
import com.example.noteapp.ui.theme.RedOrange


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
          viewModel.onEvent(NotesEvent.ToggleOrderSection)
        }) {
          Icon(imageVector = Icons.Default.Sort, contentDescription = "Sort")
        }
      }
      Spacer(modifier = Modifier.height(12.dp))
      AnimatedVisibility(
        visible = state.isOrderSectionVisible
      ) {
        OrderSection(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
          noteOrder = state.noteOrder,
          onOrderChange = {
            viewModel.onEvent(NotesEvent.Order(it))
          }
        )
      }
      NoteItem(
        note = Note(
          "test", "idemo easj esae jsahe saeh ashhsae hah ehahe hsa hesah " +
            "ejfhdshhfdhsfhhdsffhdhdfshfhdsfhdshfhdshfhdshfdshfhsdhfhdshfdshfdhdshfhshfdfhsdhfhsdfhsdhfshdfhsdhfhsfhsdhfhdsfhdshfhsdhfshdfhsdhhdfhsdhfhfdshfsha", 232131321L, R.color.dark_gray, 0
        )
      ) {}
    }
  }
}