package com.example.noteapp.featureNote.presentation

data class NoteTextFieldState(
  val text: String = "",
  val hint: String = "",
  val isHintVisible: Boolean = false
)
