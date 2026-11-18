package com.hasancbngl.bookapp.book.data.network

import com.hasancbngl.bookapp.book.data.dto.BookWorkDto
import com.hasancbngl.bookapp.book.data.dto.SearchResponseDto
import com.hasancbngl.bookapp.core.domain.DataError
import com.hasancbngl.bookapp.core.domain.Result
import io.ktor.client.HttpClient

private const val BASE_URL = "https://openlibrary.org"

class KtorRemoteBookDataSource(
    private val httpClient: HttpClient
) : RemoteBookDataSource {
    override suspend fun searchBooks(
        query: String,
        resultLimit: Int?
    ): Result<SearchResponseDto, DataError.Remote> {

    }

    override suspend fun getBookDetails(bookWorkId: String): Result<BookWorkDto, DataError.Remote> {

    }

}