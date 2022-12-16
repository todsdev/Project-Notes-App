package com.tods.notes.feature.note.domain.usecases

import com.tods.notes.feature.note.domain.usecases.add.AddNote
import com.tods.notes.feature.note.domain.usecases.delete.DeleteNote
import com.tods.notes.feature.note.domain.usecases.recover.RecoverNotes
import com.tods.notes.feature.note.domain.usecases.recoverbyid.RecoverNoteById

data class NotesUseCases(
    val recoverNotes: RecoverNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote,
    val recoverNoteById: RecoverNoteById
)