package com.example.noteapp.featureNote.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.noteapp.featureNote.presentation.addEditNote.AddEditNoteScreen
import com.example.noteapp.featureNote.presentation.notes.NotesScreen
import com.example.noteapp.featureNote.presentation.util.Screen
import com.example.noteapp.ui.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      NoteAppTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Screen.NotesScreen.route) {
          composable(route = Screen.NotesScreen.route) {
            NotesScreen(navController)
          }
          composable(
            Screen.AddEditNotesScreen.route + "?noteId={noteId}&noteColor={noteColor",
            arguments = listOf(
              navArgument
                ("noteId") {
                type = NavType.IntType
                defaultValue = -1
              },
              navArgument("noteColor") {
                type = NavType.IntType
                defaultValue = -1
              },
            )
          ) {
            val color = it.arguments?.getInt("noteColor") ?: -1
            AddEditNoteScreen(navController = navController, noteColor = color)
          }
        }
      }
    }
  }
}
