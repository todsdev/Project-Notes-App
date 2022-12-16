package com.tods.notes.feature.note.data.repository

import com.tods.notes.feature.note.data.datasource.NotesDAO
import com.tods.notes.feature.note.domain.model.NotesModel
import com.tods.notes.feature.note.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class NotesRepositoryImpl(
    private val dao: NotesDAO
): NotesRepository {

    override fun recoverNotes(): Flow<List<NotesModel>> {
        return dao.recoverNotes()
    }

    override suspend fun recoverNoteById(id: Int): NotesModel? {
        return dao.recoverNoteById(id)
    }

    override suspend fun insertNote(note: NotesModel) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: NotesModel) {
        dao.deleteNote(note)
    }
}