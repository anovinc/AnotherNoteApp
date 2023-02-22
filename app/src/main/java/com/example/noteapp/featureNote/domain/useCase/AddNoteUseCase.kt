package com.example.noteapp.featureNote.domain.useCase

import com.example.noteapp.featureNote.domain.model.InvalidNoteException
import com.example.noteapp.featureNote.domain.model.Note
import com.example.noteapp.featureNote.domain.repository.NoteRepository

class AddNoteUseCase(private val notesRepository: NoteRepository) {
  
  @Throws(InvalidNoteException::class)
  suspend operator fun invoke(note: Note) {
    if(note.title.isBlank()) {
        throw InvalidNoteException("Title of the note can't be empty.")
    }
    if(note.content.isBlank()) {
      throw InvalidNoteException("Content of the note can't be empty.")
    }
    notesRepository.createNewNote(note)
  }
}