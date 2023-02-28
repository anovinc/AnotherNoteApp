package com.example.noteapp.featureNote.presentation.notes.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.noteapp.featureNote.domain.model.Note

@Composable
fun NoteItem(
  note: Note,
  modifier: Modifier = Modifier,
  cornerRadius: Dp = 20.dp,
  onDeleteClick: (Note) -> Unit
) {
  Card(
    elevation = 4.dp,
    shape = RoundedCornerShape(cornerRadius),
    backgroundColor = colorResource(id = note.color),
    modifier = modifier
      .fillMaxWidth()
  ) {
    Column(modifier = modifier
      .padding(horizontal = 8.dp)
      .padding(top = 12.dp)) {
      Text(
        text = note.title.capitalize(),
        style = MaterialTheme.typography.h5,
        modifier = Modifier.fillMaxWidth(),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = MaterialTheme.colors.onSurface
      )
      Text(
        text = note.content,
        maxLines = 5,
        style = MaterialTheme.typography.h6,
        overflow = TextOverflow.Ellipsis,
        color = MaterialTheme.colors.onSurface
      )
      IconButton(onClick = { onDeleteClick(note) }, modifier = Modifier.align(Alignment.End)) {
        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
      }
    }
  }
  Spacer(modifier = Modifier.height(12.dp))
}