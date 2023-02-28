package com.example.noteapp.featureNote.domain.useCase

import com.example.noteapp.featureNote.domain.repository.NoteRepository

class GetNoteUseCase(private val notesRepository: NoteRepository) {

  suspend operator fun invoke(id: Int) = notesRepository.getNoteById(id)
  
 }