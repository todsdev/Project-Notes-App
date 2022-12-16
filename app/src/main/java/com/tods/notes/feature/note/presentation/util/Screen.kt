package com.tods.notes.feature.note.presentation.util

sealed class Screen(val route: String) {

    object NoteScreen: Screen("route_screen")
    object AddEditNoteScreen: Screen("add_edit_route_screen")
}
