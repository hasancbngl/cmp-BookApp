package com.hasancbngl.bookapp.book.data.repository

import androidx.sqlite.SQLiteException
import com.hasancbngl.bookapp.book.data.database.BookDao
import com.hasancbngl.bookapp.book.data.mappers.toBook
import com.hasancbngl.bookapp.book.data.mappers.toBookEntity
import com.hasancbngl.bookapp.book.data.network.RemoteBookDataSource
import com.hasancbngl.bookapp.book.domain.Book
import com.hasancbngl.bookapp.book.domain.BookRepository
import com.hasancbngl.bookapp.core.domain.DataError
import com.hasancbngl.bookapp.core.domain.EmptyResult
import com.hasancbngl.bookapp.core.domain.Result
import com.hasancbngl.bookapp.core.domain.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource,
    private val dao: BookDao
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

    override fun getFavoriteBooks(): Flow<List<Book>> {
        return dao.getFavoriteBooks()
            .map { entities ->
                entities.map { it.toBook() }
            }
    }

    override fun isBookFavorite(id: String): Flow<Boolean> {
        return dao.getFavoriteBooks()
            .map { entities ->
                entities.any { it.id == id }
            }
    }

    override suspend fun markAsFavorite(book: Book): EmptyResult<DataError.Local> {
        return try {
            dao.upsert(book.toBookEntity())
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteFromFavorites(id: String) {
        dao.deleteFavoriteBook(id)
    }


}