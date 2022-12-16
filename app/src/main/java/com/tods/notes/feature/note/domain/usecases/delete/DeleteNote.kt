package com.tods.notes.feature.note.domain.usecases.delete

import com.tods.notes.feature.note.domain.model.NotesModel
import com.tods.notes.feature.note.domain.repository.NotesRepository

class DeleteNote(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(note: NotesModel) {
        repository.deleteNote(note)
    }
}