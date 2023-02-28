package com.example.noteapp.featureNote.presentation.addEditNote

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.featureNote.domain.model.InvalidNoteException
import com.example.noteapp.featureNote.domain.model.Note
import com.example.noteapp.featureNote.domain.useCase.NoteUseCases
import com.example.noteapp.featureNote.presentation.NoteTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
  private val notesUseCases: NoteUseCases,
  savedStateHandle: SavedStateHandle // For navigation argument
) : ViewModel() {
  
  var noteTitle by mutableStateOf(
    NoteTextFieldState(
      hint = "Enter title..."
    )
  )
    private set
  
  var noteContent by mutableStateOf(NoteTextFieldState(hint = "Enter some content"))
    private set
  
  var noteColor by mutableStateOf(Note.noteColors.random().toArgb())
    private set
  
  var eventFLow = MutableSharedFlow<UiEvent>()
  
  init {
    savedStateHandle.get<Int>("noteId")?.let { noteId ->
      if (noteId != -1) {
        viewModelScope.launch {
          notesUseCases.getNoteUseCase(noteId)?.also {
            currentNoteId = it.id
            noteTitle = noteTitle.copy(text = it.title, isHintVisible = false)
            noteContent = noteContent.copy(text = it.content, isHintVisible = false)
            noteColor = it.color
          }
        }
      }
    }
  }
  
  private var currentNoteId: Int? = null
  
  fun onEvent(event: AddEditNoteEvent) {
    when (event) {
      is AddEditNoteEvent.EnteredTitle -> {
        noteTitle = noteTitle.copy(text = event.value)
      }
      is AddEditNoteEvent.ChangeTitleFocus -> {
        noteTitle = noteTitle.copy(isHintVisible = !event.focusState.isFocused && noteTitle.text.isBlank())
      }
      is AddEditNoteEvent.EnteredContent -> {
        noteContent = noteContent.copy(text = event.value)
      }
      is AddEditNoteEvent.ChangeContentFocus -> {
        noteContent = noteContent.copy(isHintVisible = !event.focusState.isFocused && noteContent.text.isBlank())
      }
      is AddEditNoteEvent.ChangeColor -> {
        noteColor = event.color
      }
      is AddEditNoteEvent.SaveNote -> {
        viewModelScope.launch {
          try {
            notesUseCases.addNoteUseCase(
              Note(
                title = noteTitle.text,
                content = noteContent.text,
                timestamp = System.currentTimeMillis(),
                color = noteColor,
                id = currentNoteId
              )
            )
            eventFLow.emit(UiEvent.SaveNote)
          } catch (e: InvalidNoteException) {
            UiEvent.ShowSnackbar(message = e.message ?: "Could not save note.")
          }
        }
      }
    }
  }
}

sealed class UiEvent {
  data class ShowSnackbar(val message: String) : UiEvent()
  object SaveNote : UiEvent()
}