package com.example.noteapp.di

import com.example.noteapp.featureNote.data.repository.NoteRepositoryImpl
import com.example.noteapp.featureNote.data.source.NoteDatabase
import com.example.noteapp.featureNote.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
  
  @Provides
  @Singleton
  fun provideNoteRepository(db: NoteDatabase): NoteRepository {
    return NoteRepositoryImpl(db.noteDao)
  }
}