package com.tods.notes.di

import android.app.Application
import androidx.room.Room
import com.tods.notes.feature.note.data.datasource.NotesDatabase
import com.tods.notes.feature.note.data.repository.NotesRepositoryImpl
import com.tods.notes.feature.note.domain.repository.NotesRepository
import com.tods.notes.feature.note.domain.usecases.NotesUseCases
import com.tods.notes.feature.note.domain.usecases.add.AddNote
import com.tods.notes.feature.note.domain.usecases.delete.DeleteNote
import com.tods.notes.feature.note.domain.usecases.recover.RecoverNotes
import com.tods.notes.feature.note.domain.usecases.recoverbyid.RecoverNoteById
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotesModule {

    @Provides
    @Singleton
    fun providesNotesDatabase(application: Application): NotesDatabase {
        return Room.databaseBuilder(
            application,
            NotesDatabase::class.java,
            NotesDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesNotesRepository(database: NotesDatabase): NotesRepository {
        return NotesRepositoryImpl(database.notesDao)
    }

    @Provides
    @Singleton
    fun providesNotesUseCases(repository: NotesRepository): NotesUseCases {
        return NotesUseCases(
            recoverNotes = RecoverNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            recoverNoteById = RecoverNoteById(repository)
        )
    }
}