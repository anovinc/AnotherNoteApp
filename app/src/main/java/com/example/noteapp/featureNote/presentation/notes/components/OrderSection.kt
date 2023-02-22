package com.example.noteapp.featureNote.presentation.notes.components

import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.noteapp.featureNote.domain.util.NoteOrder
import com.example.noteapp.featureNote.domain.util.OrderType

@Composable
fun OrderSection(
  modifier: Modifier = Modifier, noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending), onOrderChange: (NoteOrder) -> Unit
) {
  Column(modifier = modifier) {
    Row(modifier = Modifier.fillMaxWidth()) {
      DefaultRadioButton(title = "Title",
        selected = noteOrder is NoteOrder.Title,
        onSelect = { onOrderChange(NoteOrder.Title(noteOrder.order)) })
      
      Spacer(modifier = Modifier.width((8.dp)))
      
      DefaultRadioButton(title = "Date",
        selected = noteOrder is NoteOrder.Date,
        onSelect = { onOrderChange(NoteOrder.Date(noteOrder.order)) })
      
      Spacer(modifier = Modifier.width((8.dp)))
      
      DefaultRadioButton(title = "Color",
        selected = noteOrder is NoteOrder.Color,
        onSelect = { onOrderChange(NoteOrder.Color(noteOrder.order)) })
      
      Spacer(modifier = Modifier.width((16.dp)))
    }
    Row(modifier = Modifier.fillMaxWidth()) {
      DefaultRadioButton(title = "Ascending",
        selected = noteOrder is NoteOrder.Title,
        onSelect = { onOrderChange(noteOrder.copy(OrderType.Ascending)) })
      
      Spacer(modifier = Modifier.width((8.dp)))
      
      DefaultRadioButton(title = "Descending",
        selected = noteOrder is NoteOrder.Date,
        onSelect = { onOrderChange(noteOrder.copy(OrderType.Descending)) })
      Spacer(modifier = Modifier.width((8.dp)))
    }
  }
}