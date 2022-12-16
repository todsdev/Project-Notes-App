package com.tods.notes.feature.note.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tods.notes.feature.note.domain.model.NotesModel

@Database(
    entities = [NotesModel::class],
    version = 1
)
abstract class NotesDatabase: RoomDatabase() {

    abstract val notesDao: NotesDAO

    companion object {
        const val DATABASE_NAME = "notes.database"
    }
}