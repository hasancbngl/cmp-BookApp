package com.hasancbngl.bookapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.hasancbngl.bookapp.book.presentation.book_list.BookListScreenRoot
import com.hasancbngl.bookapp.book.presentation.book_list.BookListViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    val viewModel = koinViewModel<BookListViewModel>()
    MaterialTheme {
        BookListScreenRoot(
            viewModel = viewModel,
            onBookClick = {}
        )
    }
}