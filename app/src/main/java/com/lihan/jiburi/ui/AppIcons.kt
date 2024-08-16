package com.lihan.jiburi.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.lihan.jiburi.R

val StarIcon: Painter
    @Composable
    get() = painterResource(id = R.drawable.score)

val MovieIcon: Painter
    @Composable
    get() = painterResource(id = R.drawable.movie)