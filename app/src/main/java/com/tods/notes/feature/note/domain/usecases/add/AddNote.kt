package com.tods.notes.feature.note.domain.usecases.add

import com.tods.notes.feature.note.domain.model.InvalidNoteException
import com.tods.notes.feature.note.domain.model.NotesModel
import com.tods.notes.feature.note.domain.repository.NotesRepository

class AddNote(private val repository: NotesRepository) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: NotesModel) {
        if(note.title.isBlank()) {
            throw InvalidNoteException("The title of the note can't be empty")
        }
        if(note.content.isBlank()) {
            throw InvalidNoteException("The content of the note can't be empty")
        }
        repository.insertNote(note)
    }
}