package com.example.noteapp.featureNote.domain.useCase

import com.example.noteapp.featureNote.domain.model.Note
import com.example.noteapp.featureNote.domain.repository.NoteRepository

class DeleteNoteUseCase(private val noteRepository: NoteRepository) {
  
  suspend operator fun invoke(note: Note) {
    noteRepository.deleteNote(note)
  }
}