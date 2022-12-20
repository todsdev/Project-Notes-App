package com.tods.notes.feature.note.domain.usecases.recover

import com.google.common.truth.Truth.assertThat
import com.tods.notes.feature.note.domain.model.NotesModel
import com.tods.notes.feature.note.domain.repository.FakeNotesRepository
import com.tods.notes.feature.note.domain.util.NotesOrder
import com.tods.notes.feature.note.domain.util.OrderType
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class RecoverNotesTest {

    private lateinit var recoverNotes: RecoverNotes
    private lateinit var fakeRepository: FakeNotesRepository

    @Before
    fun initialSettings() {
        fakeRepository = FakeNotesRepository()
        recoverNotes = RecoverNotes(fakeRepository)

        val insertNotes = mutableListOf<NotesModel>()
        ('a'..'z').forEachIndexed { index, c ->
            insertNotes.add(
                NotesModel(
                    title = c.toString(),
                    content = c.toString(),
                    timestamp = index.toLong(),
                    color = index
                )
            )
        }
        insertNotes.shuffle()
        runBlocking {
            insertNotes.forEach { fakeRepository.insertNote(it) }
        }
    }

    @Test
    fun `Order notes by title ascending, correct order`() = runBlocking {
        val notes = recoverNotes(NotesOrder.Title(OrderType.Ascending)).first()
        for(i in 0..notes.size - 2) {
            assertThat(notes[i].title).isLessThan(notes[i+1].title)
        }
    }

    @Test
    fun `Order notes by title descending, correct order`() = runBlocking {
        val notes = recoverNotes(NotesOrder.Title(OrderType.Descending)).first()
        for(i in 0..notes.size -2) {
            assertThat(notes[i].title).isGreaterThan(notes[i+1].title)
        }
    }

    @Test
    fun `Order notes by date ascending, correct order`() = runBlocking {
        val notes = recoverNotes(NotesOrder.Date(OrderType.Ascending)).first()
        for (i in 0..notes.size - 2) {
            assertThat(notes[i].timestamp).isLessThan(notes[i+1].timestamp)
        }
    }

    @Test
    fun `Order notes by date descending, correct order`() = runBlocking {
        val notes = recoverNotes(NotesOrder.Date(OrderType.Descending)).first()
        for (i in 0..notes.size - 2) {
            assertThat(notes[i].timestamp).isGreaterThan(notes[i+1].timestamp)
        }
    }

    @Test
    fun `Order notes by color ascending, correct order`() = runBlocking {
        val notes = recoverNotes(NotesOrder.Color(OrderType.Ascending)).first()
        for (i in 0..notes.size - 2) {
            assertThat(notes[i].color).isLessThan(notes[i+1].color)
        }
    }

    @Test
    fun `Order notes by color descending, correct order`() = runBlocking {
        val notes = recoverNotes(NotesOrder.Color(OrderType.Descending)).first()
        for (i in 0..notes.size - 2) {
            assertThat(notes[i].color).isGreaterThan(notes[i+1].color)
        }
    }
}