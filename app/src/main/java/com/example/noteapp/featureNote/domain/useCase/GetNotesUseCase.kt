package com.example.noteapp.featureNote.domain.useCase

import com.example.noteapp.featureNote.domain.model.Note
import com.example.noteapp.featureNote.domain.repository.NoteRepository
import com.example.noteapp.featureNote.domain.util.NoteOrder
import com.example.noteapp.featureNote.domain.util.OrderType

class GetNotesUseCase(private val notesRepository: NoteRepository) {
  
  suspend operator fun invoke(noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)): List<Note> {
    val notes  = notesRepository.getNotes()
    when (noteOrder.order) {
      is OrderType.Ascending -> {
        when (noteOrder) {
          is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
          is NoteOrder.Date -> notes.sortedBy { it.timestamp }
          is NoteOrder.Color -> notes.sortedBy { it.color }
    
        }
      }
        OrderType.Descending -> {
          when(noteOrder) {
            is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
            is NoteOrder.Date -> notes.sortedByDescending { it.timestamp }
            is NoteOrder.Color -> notes.sortedByDescending { it.color }
        }
      }
    }
    return notes
  }
}