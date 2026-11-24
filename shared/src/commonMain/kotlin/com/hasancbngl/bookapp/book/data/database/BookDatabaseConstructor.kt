package com.hasancbngl.bookapp.book.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("KotlinNoActualForExpect")
expect object BookDatabaseConstructor : RoomDatabaseConstructor<BookDatabase> {
    override fun initialize(): BookDatabase
}