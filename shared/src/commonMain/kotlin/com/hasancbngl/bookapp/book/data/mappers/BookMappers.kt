package com.hasancbngl.bookapp.book.data.mappers

import com.hasancbngl.bookapp.book.data.database.BookEntity
import com.hasancbngl.bookapp.book.data.dto.SearchedBookDto
import com.hasancbngl.bookapp.book.domain.Book

fun SearchedBookDto.toBook(): Book {
    return Book(
        id = id.substringAfterLast("/"),
        title = title,
        imageUrl = if (coverKey != null) {
            "https://covers.openlibrary.org/b/olid/${coverKey}-L.jpg"
        } else {
            "https://covers.openlibrary.org/b/id/${coverAlternativeKey}-L.jpg"
        },
        authors = authorNames ?: emptyList(),
        description = null,
        languages = languages ?: emptyList(),
        firstPublishedYear = firstPublishYear.toString(),
        averageRating = ratingsAverage,
        ratingCount = ratingsCount,
        numberOfPages = numPagesMedian,
        numberOfEditions = numEditions ?: 0
    )
}

fun Book.toBookEntity(): BookEntity {
    return BookEntity(
        id = id,
        title = title,
        description = description,
        imageUrl = imageUrl,
        languages = languages,
        authors = authors,
        firstPublishYear = firstPublishedYear,
        ratingsAverage = averageRating,
        ratingsCount = ratingCount,
        numPagesMedian = numberOfPages,
        numEditions = numberOfEditions
    )
}

fun BookEntity.toBook(): Book {
    return Book(
        id = id,
        title = title,
        description = description,
        imageUrl = imageUrl,
        languages = languages,
        authors = authors,
        firstPublishedYear = firstPublishYear,
        averageRating = ratingsAverage,
        ratingCount = ratingsCount,
        numberOfPages = numPagesMedian,
        numberOfEditions = numEditions
    )
}