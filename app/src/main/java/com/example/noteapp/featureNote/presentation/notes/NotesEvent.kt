package com.example.noteapp.featureNote.presentation.notes

import com.example.noteapp.featureNote.domain.model.Note
import com.example.noteapp.featureNote.domain.util.NoteOrder

sealed class NotesEvent {
  data class Order(val noteOrder: NoteOrder): NotesEvent()
  data class DeleteNote(val note: Note): NotesEvent()
  object RestoreNote: NotesEvent()
  object ToggleOrderSection: NotesEvent()
}
