package com.example.noteapp.featureNote.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noteapp.ui.theme.*

@Entity
data class Note(
  val title: String,
  val content: String,
  val timestamp: Long,
  val color: Int,
  @PrimaryKey val id: Int? = null
) {
  // Control which colors user can chose for note
  companion object {
    val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
  }
}

class InvalidNoteException(message: String): Exception(message)
