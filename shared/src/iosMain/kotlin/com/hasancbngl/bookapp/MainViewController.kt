package com.hasancbngl.bookapp

import androidx.compose.ui.window.ComposeUIViewController
import com.hasancbngl.bookapp.app.App
import com.hasancbngl.bookapp.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }) { App() }