package com.example.noteapp.featureNote.domain.useCase

//Wrap use cases for single feature/viewmodel in case viewmodel constructor became massive
data class NoteUseCases(
  val getNotesUseCase: GetNotesUseCase,
  val deleteNoteUseCase: DeleteNoteUseCase,
  val addNoteUseCase: AddNoteUseCase
)
