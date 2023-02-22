package com.example.noteapp.featureNote.domain.repository

import com.example.noteapp.featureNote.domain.model.Note

interface NoteRepository {
  
  suspend fun getNotes(): List<Note>
  
  suspend fun getNoteById(id: Int): Note?
  
  suspend fun createNewNote(note: Note)
  
  suspend fun deleteNote(note: Note)
  
}