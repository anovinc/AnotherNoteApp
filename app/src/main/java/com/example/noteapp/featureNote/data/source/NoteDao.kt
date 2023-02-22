package com.example.noteapp.featureNote.data.source

import androidx.room.*
import com.example.noteapp.featureNote.domain.model.Note

@Dao
interface NoteDao {
  
  @Query("SELECT * FROM note")
  suspend fun getNotes(): List<Note>
  
  @Query("SELECT * FROM NOTE WHERE id = :id")
  suspend fun getNoteById(id: Int): Note?
  
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun createNewNote(note: Note)

  @Delete
  suspend fun deleteNote(note: Note)
}