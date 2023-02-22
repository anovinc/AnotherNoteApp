package com.example.noteapp.di

import com.example.noteapp.featureNote.domain.repository.NoteRepository
import com.example.noteapp.featureNote.domain.useCase.AddNoteUseCase
import com.example.noteapp.featureNote.domain.useCase.DeleteNoteUseCase
import com.example.noteapp.featureNote.domain.useCase.GetNotesUseCase
import com.example.noteapp.featureNote.domain.useCase.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
  
  @Provides
  @Singleton
  fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
    return NoteUseCases(
      getNotesUseCase = GetNotesUseCase(repository),
      deleteNoteUseCase = DeleteNoteUseCase(repository),
      addNoteUseCase = AddNoteUseCase(repository)
    )
  }
}