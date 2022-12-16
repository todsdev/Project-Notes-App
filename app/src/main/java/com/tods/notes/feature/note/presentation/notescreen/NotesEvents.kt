package com.tods.notes.feature.note.presentation.notescreen

import com.tods.notes.feature.note.domain.model.NotesModel
import com.tods.notes.feature.note.domain.util.NotesOrder

sealed class NotesEvents {

    data class Order(val notesOrder: NotesOrder): NotesEvents()
    data class Delete(val note: NotesModel): NotesEvents()
    object Restore: NotesEvents()
    object ToggleOrderSection: NotesEvents()
}