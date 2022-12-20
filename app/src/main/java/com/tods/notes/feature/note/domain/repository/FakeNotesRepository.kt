package com.tods.notes.feature.note.domain.repository

import com.tods.notes.feature.note.domain.model.NotesModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeNotesRepository: NotesRepository {

    private val notes = mutableListOf<NotesModel>()

    override fun recoverNotes(): Flow<List<NotesModel>> {
        return flow { emit(notes) }
    }

    override suspend fun recoverNoteById(id: Int): NotesModel? {
        return notes.find { it.id == id }
    }

    override suspend fun insertNote(note: NotesModel) {
        notes.add(note)
    }

    override suspend fun deleteNote(note: NotesModel) {
        notes.remove(note)
    }
}