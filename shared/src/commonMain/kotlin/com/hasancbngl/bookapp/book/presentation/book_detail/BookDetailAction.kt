package com.hasancbngl.bookapp.book.presentation.book_detail

import com.hasancbngl.bookapp.book.domain.Book

sealed interface BookDetailAction {
    data object OnBackClick : BookDetailAction
    data object OnFavoriteClick : BookDetailAction
    data class OnSelectedBookChange(val book: Book) : BookDetailAction
}