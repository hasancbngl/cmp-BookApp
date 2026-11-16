package com.hasancbngl.bookapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hasancbngl.bookapp.book.presentation.book_list.BookListScreenRoot
import com.hasancbngl.bookapp.book.presentation.book_list.BookListViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        BookListScreenRoot(
            viewModel = remember { BookListViewModel() },
            onBookClick = {}
        )
    }
}