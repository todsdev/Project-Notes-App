package com.tods.notes.feature.note.data.datasource

import androidx.room.*
import com.tods.notes.feature.note.domain.model.NotesModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDAO {

    @Query("SELECT * FROM notes")
    fun recoverNotes(): Flow<List<NotesModel>>

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun recoverNoteById(id: Int): NotesModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NotesModel)

    @Delete
    suspend fun deleteNote(note: NotesModel)
}