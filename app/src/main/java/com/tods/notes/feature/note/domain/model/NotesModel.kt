package com.tods.notes.feature.note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tods.notes.ui.theme.*

@Entity(tableName = "notes")
data class NotesModel(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey
    val id: Int? = null
) {
    companion object {
        val noteColors = listOf(RedOrange, Violet, BabyBlue, RedPink, LightGreen)
    }
}

class InvalidNoteException(message: String): Exception(message)
