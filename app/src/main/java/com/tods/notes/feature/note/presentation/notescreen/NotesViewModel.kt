package com.tods.notes.feature.note.presentation.notescreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tods.notes.feature.note.domain.model.NotesModel
import com.tods.notes.feature.note.domain.usecases.NotesUseCases
import com.tods.notes.feature.note.domain.util.NotesOrder
import com.tods.notes.feature.note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val useCases: NotesUseCases): ViewModel() {
    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var recentlyDeletedNote: NotesModel? = null

    private var recoverNotesJob: Job? = null

    init {
        recoverNotes(NotesOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvents) {
        when(event) {
            is NotesEvents.Delete -> {
                viewModelScope.launch {
                    useCases.deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }
            }
            is NotesEvents.Order -> {
                viewModelScope.launch {
                    if(state.value.notesOrder::class == event.notesOrder::class &&
                            state.value.notesOrder.orderType == state.value.notesOrder.orderType) {
                        return@launch
                    }
                    recoverNotes(event.notesOrder)
                }
            }
            is NotesEvents.Restore -> {
                viewModelScope.launch {
                    useCases.addNote(recentlyDeletedNote?: return@launch)
                    recentlyDeletedNote = null
                }
            }
            is NotesEvents.ToggleOrderSection -> {
                _state.value = state.value.copy(isOrderSectionVisible = !state.value.isOrderSectionVisible)
            }
        }
    }

    private fun recoverNotes(notesOrder: NotesOrder) {
        recoverNotesJob?.cancel()
        recoverNotesJob = useCases.recoverNotes(notesOrder).onEach { notes ->
            _state.value = state.value.copy(
                notes = notes,
                notesOrder = notesOrder
            )
        }.launchIn(viewModelScope)
    }
}