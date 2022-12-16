package com.tods.notes.feature.note.domain.util

sealed class NotesOrder(
    val orderType: OrderType
) {

    class Title(orderType: OrderType): NotesOrder(orderType)
    class Date(orderType: OrderType): NotesOrder(orderType)
    class Color(orderType: OrderType): NotesOrder(orderType)

    fun copy(orderType: OrderType): NotesOrder {
        return when(this) {
            is NotesOrder.Title -> NotesOrder.Title(orderType)
            is NotesOrder.Date -> NotesOrder.Date(orderType)
            is NotesOrder.Color -> NotesOrder.Color(orderType)
        }
    }
}


