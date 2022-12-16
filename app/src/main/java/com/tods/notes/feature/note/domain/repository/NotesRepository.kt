package com.tods.notes.feature.note.domain.repository

import com.tods.notes.feature.note.domain.model.NotesModel
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    fun recoverNotes(): Flow<List<NotesModel>>

    suspend fun recoverNoteById(id: Int): NotesModel?

    suspend fun insertNote(note: NotesModel)

    suspend fun deleteNote(note: NotesModel)
}