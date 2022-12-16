package com.tods.notes.feature.note.domain.usecases.recoverbyid

import com.tods.notes.feature.note.domain.model.NotesModel
import com.tods.notes.feature.note.domain.repository.NotesRepository

class RecoverNoteById(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(id: Int): NotesModel? {
        return repository.recoverNoteById(id)
    }
}