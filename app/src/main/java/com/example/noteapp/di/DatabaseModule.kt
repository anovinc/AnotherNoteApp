package com.example.noteapp.di

import android.app.Application
import androidx.room.Room
import com.example.noteapp.featureNote.data.source.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
  
  @Provides
  @Singleton
  fun provideNoteDatabase(app: Application): NoteDatabase {
    return Room.databaseBuilder(
      app,
      NoteDatabase::class.java,
      NoteDatabase.DATABASE_NAME
    ).build()
  }
}