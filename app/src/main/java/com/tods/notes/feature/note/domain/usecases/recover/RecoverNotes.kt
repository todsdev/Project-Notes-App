package com.tods.notes.feature.note.domain.usecases.recover

import com.tods.notes.feature.note.domain.model.NotesModel
import com.tods.notes.feature.note.domain.repository.NotesRepository
import com.tods.notes.feature.note.domain.util.NotesOrder
import com.tods.notes.feature.note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

class RecoverNotes(
    private val repository: NotesRepository
) {

    operator fun invoke(
        notesOrder: NotesOrder = NotesOrder.Date(OrderType.Descending)
    ): Flow<List<NotesModel>> {
        return repository.recoverNotes().map { notes ->
            when(notesOrder.orderType) {
                is OrderType.Descending -> {
                    when(notesOrder) {
                        is NotesOrder.Date -> notes.sortedBy { it.timestamp }
                        is NotesOrder.Title -> notes.sortedBy { it.title.lowercase(Locale.ROOT) }
                        is NotesOrder.Color -> notes.sortedBy { it.color }
                    }
                }
                is OrderType.Ascending -> {
                    when(notesOrder) {
                        is NotesOrder.Date -> notes.sortedByDescending { it.timestamp }
                        is NotesOrder.Title -> notes.sortedByDescending { it.title.lowercase(Locale.ROOT) }
                        is NotesOrder.Color -> notes.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}