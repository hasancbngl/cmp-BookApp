package com.hasancbngl.bookapp.book.data.network

import com.hasancbngl.bookapp.book.data.dto.BookWorkDto
import com.hasancbngl.bookapp.book.data.dto.SearchResponseDto
import com.hasancbngl.bookapp.core.domain.DataError
import com.hasancbngl.bookapp.core.domain.Result

interface RemoteBookDataSource {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): Result<SearchResponseDto, DataError.Remote>

    suspend fun getBookDetails(bookWorkId : String) : Result<BookWorkDto, DataError.Remote>
}