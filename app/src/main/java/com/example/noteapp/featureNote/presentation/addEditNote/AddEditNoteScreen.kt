package com.example.noteapp.featureNote.presentation.addEditNote

import android.annotation.SuppressLint
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.noteapp.featureNote.domain.model.Note
import com.example.noteapp.featureNote.presentation.addEditNote.components.TransparentHintTextField
import com.example.noteapp.featureNote.presentation.util.Screen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddEditNoteScreen(
  navController: NavController,
  noteColor: Int,
  viewModel: AddEditNoteViewModel = hiltViewModel()
) {
  val titleState = viewModel.noteTitle
  val contentState = viewModel.noteContent
  val scaffoldState = rememberScaffoldState()
  
  val noteBackgroundAnimatable = remember {
    Animatable(
      Color(if (noteColor != -1) noteColor else viewModel.noteColor)
    )
  }
  val coroutineScope = rememberCoroutineScope()
  LaunchedEffect(key1 = true) {
    viewModel.eventFLow.collectLatest { event ->
      when (event) {
        is UiEvent.ShowSnackbar -> {
          scaffoldState.snackbarHostState.showSnackbar(message = event.message)
        }
        is UiEvent.SaveNote -> {
          navController.popBackStack(Screen.NotesScreen.route, false)
        }
      }
    }
    
  }
  Scaffold(floatingActionButton = {
    FloatingActionButton(
      onClick = { viewModel.onEvent(AddEditNoteEvent.SaveNote) },
      backgroundColor = MaterialTheme.colors.primary
    ) {
      Icon(imageVector = Icons.Default.Save, contentDescription = "Save note")
    }
  }, scaffoldState = scaffoldState) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .background(noteBackgroundAnimatable.value)
        .padding(16.dp)
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Note.noteColors.forEach { color ->
          val colorInt = color.toArgb()
          Box(
            modifier = Modifier
              .size(50.dp)
              .shadow(15.dp, CircleShape)
              .clip(CircleShape)
              .background(color)
              .border(
                width = 3.dp,
                color = if (viewModel.noteColor == colorInt) {
                  Color.Black
                } else Color.Transparent,
                shape = CircleShape
              )
              .clickable {
                coroutineScope.launch {
                  noteBackgroundAnimatable.animateTo(
                    targetValue = Color(colorInt),
                    animationSpec = tween(
                      durationMillis = 500
                    )
                  )
                }
                viewModel.onEvent(AddEditNoteEvent.ChangeColor(colorInt))
              }
          )
        }
      }
      Spacer(modifier = Modifier.height(16.dp))
      TransparentHintTextField(
        text = titleState.text,
        hint = titleState.hint,
        onValueChange = {
          viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it))
        },
        onFocusChange = {
          viewModel.onEvent(AddEditNoteEvent.ChangeTitleFocus(it))
        },
        isHintVisible = titleState.isHintVisible,
        singleLine = true,
        textStyle = MaterialTheme.typography.h5
      )
      Spacer(modifier = Modifier.height(16.dp))
      TransparentHintTextField(
        text = contentState.text,
        hint = contentState.hint,
        onValueChange = {
          viewModel.onEvent(AddEditNoteEvent.EnteredContent(it))
        },
        onFocusChange = {
          viewModel.onEvent(AddEditNoteEvent.ChangeContentFocus(it))
        },
        isHintVisible = contentState.isHintVisible,
        textStyle = MaterialTheme.typography.body1,
        modifier = Modifier.fillMaxHeight()
      )
    }
  }
}