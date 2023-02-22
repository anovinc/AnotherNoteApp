package com.example.noteapp.featureNote.presentation.notes

import com.example.noteapp.featureNote.domain.model.Note
import com.example.noteapp.featureNote.domain.util.NoteOrder
import com.example.noteapp.featureNote.domain.util.OrderType

data class NotesState(
  val notes: List<Note> = emptyList(),
  val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
  val isOrderSectionVisible: Boolean = false
)
