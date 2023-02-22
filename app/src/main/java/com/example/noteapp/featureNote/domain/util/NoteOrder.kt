package com.example.noteapp.featureNote.domain.util

sealed class NoteOrder(val order: OrderType) {
  class Title(orderType: OrderType): NoteOrder(orderType)
  class Date(orderType: OrderType): NoteOrder(orderType)
  class Color(orderType: OrderType): NoteOrder(orderType)
  
}
