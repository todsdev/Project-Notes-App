package com.tods.notes.feature.note.presentation.addeditnotescreen

import androidx.compose.ui.focus.FocusState

sealed class AddEditNotesEvents {

        data class EnteredTitle(val value: String):AddEditNotesEvents()
        data class ChangeTitleFocus(val focusState: FocusState):AddEditNotesEvents()
        data class EnteredContent(val value: String):AddEditNotesEvents()
        data class ChangeContentFocus(val focusState: FocusState):AddEditNotesEvents()
        data class ChangeColor(val color: Int): AddEditNotesEvents()
        object SaveNote: AddEditNotesEvents()
}