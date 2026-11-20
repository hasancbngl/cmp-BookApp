package com.hasancbngl.bookapp.book.presentation.book_list

import com.hasancbngl.bookapp.book.domain.Book

data class BookListState(
    val searchQuery: String = "kotlin",
    val searchResults: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = true,
    val selectedTabIndex: Int = 0,
    val errorMessage: String? = ""
)