package com.tods.notes.feature.note.domain.usecases.add

import com.google.common.truth.Truth.assertThat
import com.tods.notes.feature.note.domain.model.InvalidNoteException
import com.tods.notes.feature.note.domain.model.NotesModel
import com.tods.notes.feature.note.domain.repository.FakeNotesRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AddNoteTest {

    private lateinit var addNote: AddNote
    private lateinit var fakeRepository: FakeNotesRepository
    private lateinit var note: NotesModel

    @Before
    fun initialSettings() {
        fakeRepository = FakeNotesRepository()
        addNote = AddNote(fakeRepository)
    }

    @Test(expected = InvalidNoteException::class)
    fun `Add note with empty title, throws exception`(): Unit = runBlocking {
        note = NotesModel(
            title = "",
            content = "Content",
            timestamp = 10,
            color = 10
        )
        addNote(note = note)
        val exception = InvalidNoteException("Error")
        assertThat(exception).hasCauseThat().isInstanceOf(InvalidNoteException::class.java)
    }

    @Test(expected = InvalidNoteException::class)
    fun `Add note with empty content, throws exception`() = runBlocking {
        note = NotesModel(
            title = "Title",
            content = "",
            timestamp = 10,
            color = 10
        )
        addNote(note = note)
        val exception = InvalidNoteException("Error")
        assertThat(exception).hasCauseThat().isInstanceOf(InvalidNoteException::class.java)
    }

    @Test
    fun `Add note full field characteristics`(): Unit = runBlocking {
        note = NotesModel(
            title = "Title",
            content = "Content",
            timestamp = 10,
            color = 10
        )
        addNote(note = note)
        assertThat(fakeRepository.recoverNotes().first().contains(note))
    }
}