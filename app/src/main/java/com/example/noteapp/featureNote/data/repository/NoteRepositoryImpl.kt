package com.example.noteapp.featureNote.data.repository

import com.example.noteapp.featureNote.data.source.NoteDao
import com.example.noteapp.featureNote.domain.model.Note
import com.example.noteapp.featureNote.domain.repository.NoteRepository

class NoteRepositoryImpl(private val dao: NoteDao) : NoteRepository {
  
  override suspend fun getNotes(): List<Note> = dao.getNotes()
  
  override suspend fun getNoteById(id: Int): Note? = dao.getNoteById(id)
  
  override suspend fun createNewNote(note: Note) {
    dao.createNewNote(note)
  }
  
  override suspend fun deleteNote(note: Note) {
    dao.deleteNote(note)
  }
  
}