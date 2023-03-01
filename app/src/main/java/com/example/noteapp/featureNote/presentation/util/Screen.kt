package com.example.noteapp.featureNote.presentation.util

sealed class Screen(val route: String) {
  object NotesScreen: Screen("notesScreen")
  object AddEditNotesScreen: Screen("addEditNotesScreen")
}
