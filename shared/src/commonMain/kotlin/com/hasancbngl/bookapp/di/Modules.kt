package com.hasancbngl.bookapp.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.hasancbngl.bookapp.book.data.database.BookDatabase
import com.hasancbngl.bookapp.book.data.database.DatabaseFactory
import com.hasancbngl.bookapp.book.data.network.KtorRemoteBookDataSource
import com.hasancbngl.bookapp.book.data.network.RemoteBookDataSource
import com.hasancbngl.bookapp.book.data.repository.DefaultBookRepository
import com.hasancbngl.bookapp.book.domain.BookRepository
import com.hasancbngl.bookapp.book.presentation.SharedBookViewModel
import com.hasancbngl.bookapp.book.presentation.book_detail.BookDetailViewModel
import com.hasancbngl.bookapp.book.presentation.book_list.BookListViewModel
import com.hasancbngl.bookapp.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()
    singleOf(::DefaultBookRepository).bind<BookRepository>()

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }

    single { get<BookDatabase>().bookDao }

    viewModelOf(::BookListViewModel)
    viewModelOf(::BookDetailViewModel)
    viewModelOf(::SharedBookViewModel)
}