package com.tods.notes.feature.note.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
