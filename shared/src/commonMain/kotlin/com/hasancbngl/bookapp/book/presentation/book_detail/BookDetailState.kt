package com.hasancbngl.bookapp.book.presentation.book_detail

import com.hasancbngl.bookapp.book.domain.Book

data class BookDetailState(
    val isLoading: Boolean = true,
    val isFavorite: Boolean = false,
    val book: Book? = null
)