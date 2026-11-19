package com.hasancbngl.bookapp.book.domain

import com.hasancbngl.bookapp.core.domain.DataError
import com.hasancbngl.bookapp.core.domain.Result

interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
}