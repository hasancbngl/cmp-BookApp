package com.hasancbngl.bookapp.book.data.repository

import com.hasancbngl.bookapp.book.data.mappers.toBook
import com.hasancbngl.bookapp.book.data.network.RemoteBookDataSource
import com.hasancbngl.bookapp.book.domain.Book
import com.hasancbngl.bookapp.book.domain.BookRepository
import com.hasancbngl.bookapp.core.domain.DataError
import com.hasancbngl.bookapp.core.domain.Result
import com.hasancbngl.bookapp.core.domain.map

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource
) : BookRepository {
    override suspend fun searchBooks(
        query: String
    ): Result<List<Book>, DataError.Remote> {
        return remoteBookDataSource
            .searchBooks(query)
            .map { dto ->
                dto.results.map { it.toBook() }
            }
    }

    override suspend fun getBookDescription(bookId: String): Result<String?, DataError> {
        return remoteBookDataSource
            .getBookDetails(bookId)
            .map { it.description }
    }


}