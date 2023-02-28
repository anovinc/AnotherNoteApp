package com.example.noteapp.di

import com.example.noteapp.featureNote.domain.repository.NoteRepository
import com.example.noteapp.featureNote.domain.useCase.*
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
      addNoteUseCase = AddNoteUseCase(repository),
      getNoteUseCase = GetNoteUseCase(repository)
    )
  }
}