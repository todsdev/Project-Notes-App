package com.tods.notes.feature.note.presentation.addeditnotescreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tods.notes.feature.note.domain.model.InvalidNoteException
import com.tods.notes.feature.note.domain.model.NotesModel
import com.tods.notes.feature.note.domain.usecases.NotesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNotesViewModel @Inject constructor(
    private val notesUseCases: NotesUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _noteTitle = mutableStateOf<AddEditNotesTextFieldState>(AddEditNotesTextFieldState(
        hint = "Enter title..."
    ))
    val noteTitle: State<AddEditNotesTextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf<AddEditNotesTextFieldState>(AddEditNotesTextFieldState(
        hint = "Enter some content..."
    ))
    val noteContent: State<AddEditNotesTextFieldState> = _noteContent

    private val _noteColor = mutableStateOf<Int>(NotesModel.noteColors.random().toArgb())
    val noteColor: State<Int> = _noteColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if(noteId != -1) {
                viewModelScope.launch {
                    notesUseCases.recoverNoteById(noteId)?.also { note ->
                        currentNoteId = note.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _noteContent.value = noteContent.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )
                        _noteColor.value = note.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditNotesEvents) {
        when(event) {
            is AddEditNotesEvents.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditNotesEvents.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteTitle.value.text.isBlank()
                )
            }
            is AddEditNotesEvents.EnteredContent -> {
                _noteContent.value = noteContent.value.copy(
                    text = event.value
                )
            }
            is AddEditNotesEvents.ChangeContentFocus -> {
                _noteContent.value = noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteContent.value.text.isBlank()
                )
            }
            is AddEditNotesEvents.ChangeColor -> {
                _noteColor.value = event.color
            }
            is AddEditNotesEvents.SaveNote -> {
                viewModelScope.launch {
                    try {
                        notesUseCases.addNote(
                            NotesModel(
                                title = noteTitle.value.text,
                                content = noteContent.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = noteColor.value,
                                id = currentNoteId
                             )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch(e: InvalidNoteException) {
                        _eventFlow.emit(UiEvent.ShowSnackBar(
                            message = e.message?: "An error occurred"
                        ))
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String): UiEvent()
        object SaveNote: UiEvent()
    }
}