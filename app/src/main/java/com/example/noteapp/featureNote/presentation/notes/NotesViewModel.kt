package com.example.noteapp.featureNote.presentation.notes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.featureNote.domain.model.Note
import com.example.noteapp.featureNote.domain.useCase.NoteUseCases
import com.example.noteapp.featureNote.domain.util.NoteOrder
import com.example.noteapp.featureNote.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val notesUseCases: NoteUseCases) : ViewModel() {
  
  var state by mutableStateOf(NotesState())
    private set
  
  private var recentlyDeletedNote: Note? = null
  
  init {
    getNotes(NoteOrder.Date(OrderType.Descending))
  }
  fun onEvent(event: NotesEvent) {
    when (event) {
      is NotesEvent.Order -> {
        //If user clicks on the same radio button that is already checked we want do nothing
        if (state.noteOrder::class == event.noteOrder::class && state.noteOrder.order == event.noteOrder.order) {
          return
        }
        getNotes(event.noteOrder)
      }
      is NotesEvent.ToggleOrderSection -> {
        state = state.copy(isOrderSectionVisible = !state.isOrderSectionVisible)
      }
      is NotesEvent.DeleteNote -> {
        viewModelScope.launch {
          notesUseCases.deleteNoteUseCase(event.note)
          recentlyDeletedNote = event.note
        }
      }
      is NotesEvent.RestoreNote -> {
        viewModelScope.launch {
          notesUseCases.addNoteUseCase(recentlyDeletedNote ?: return@launch)
          recentlyDeletedNote = null
        }
      }
    }
  }
  
  private fun getNotes(noteOrder: NoteOrder) {
    viewModelScope.launch {
      val notes = notesUseCases.getNotesUseCase(noteOrder)
      state = state.copy(notes = notes, noteOrder = noteOrder)
    }
  }
}