package com.hasancbngl.bookapp.book.presentation.book_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import bookapp.shared.generated.resources.Res
import bookapp.shared.generated.resources.description_unavailable
import bookapp.shared.generated.resources.languages
import bookapp.shared.generated.resources.pages
import bookapp.shared.generated.resources.rating
import bookapp.shared.generated.resources.synopsis
import com.hasancbngl.bookapp.book.presentation.book_detail.components.BlurredImageBackground
import com.hasancbngl.bookapp.book.presentation.book_detail.components.BookChip
import com.hasancbngl.bookapp.book.presentation.book_detail.components.ChipSize
import com.hasancbngl.bookapp.book.presentation.book_detail.components.TitledContent
import com.hasancbngl.bookapp.core.presentation.SandYellow
import org.jetbrains.compose.resources.stringResource
import kotlin.math.round

@Composable
fun BookDetailScreenRoot(
    viewModel: BookDetailViewModel,
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    BookDetailScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is BookDetailAction.OnBackClick -> onBackClick()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
private fun BookDetailScreen(
    state: BookDetailState,
    onAction: (BookDetailAction) -> Unit
) {
    BlurredImageBackground(
        imageUrl = state.book?.imageUrl,
        isFavorite = state.isFavorite,
        onFavoriteClick = {
            onAction(BookDetailAction.OnFavoriteClick)
        },
        onBackClick = {
            onAction(BookDetailAction.OnBackClick)
        },
        modifier = Modifier.fillMaxSize()
    ) {
        //content
        if (state.book != null) {
            //show book details
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 16.dp,
                        horizontal = 24.dp
                    )
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = state.book.title,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = state.book.authors.joinToString(),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    state.book.averageRating?.let { rating ->
                        TitledContent(title = stringResource(Res.string.rating)) {
                            BookChip {
                                Text(
                                    text = "${round(rating * 10) / 10.0}"
                                )
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = null,
                                    tint = SandYellow
                                )
                            }
                        }
                    }
                    state.book.numberOfPages?.let { pageCount ->
                        TitledContent(
                            title = stringResource(Res.string.pages),
                        ) {
                            BookChip {
                                Text(text = pageCount.toString())
                            }
                        }
                    }
                }
                if (state.book.languages.isNotEmpty()) {
                    TitledContent(
                        title = stringResource(Res.string.languages),
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                    ) {
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.wrapContentSize(Alignment.Center)
                        ) {
                            state.book.languages.forEach { language ->
                                BookChip(
                                    modifier = Modifier
                                        .padding(2.dp)
                                ) {
                                    Text(
                                        text = language.uppercase(),
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                        }
                    }
                }
                Text(
                    text = stringResource(Res.string.synopsis),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .fillMaxWidth()
                        .padding(
                            top = 24.dp,
                            bottom = 8.dp
                        )
                )
                if(state.isLoading) {
                    CircularProgressIndicator()
                } else {
                    Text(
                        text = if(state.book.description.isNullOrBlank()) {
                            stringResource(Res.string.description_unavailable)
                        } else {
                            state.book.description
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Justify,
                        color = if(state.book.description.isNullOrBlank()) {
                            Color.Black.copy(alpha = 0.4f)
                        } else Color.Black,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}