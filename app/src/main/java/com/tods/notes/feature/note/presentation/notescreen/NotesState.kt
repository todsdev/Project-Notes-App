package com.tods.notes.feature.note.presentation.notescreen

import com.tods.notes.feature.note.domain.model.NotesModel
import com.tods.notes.feature.note.domain.util.NotesOrder
import com.tods.notes.feature.note.domain.util.OrderType

data class NotesState(
    val notes: List<NotesModel> = emptyList(),
    val notesOrder: NotesOrder = NotesOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)