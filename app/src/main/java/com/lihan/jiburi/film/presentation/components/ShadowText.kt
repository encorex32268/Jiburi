package com.lihan.jiburi.film.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ShadowText(
    text: String,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    blurRadius: Dp = 4.dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ){
        Text(
            modifier = Modifier.blur(blurRadius),
            text = text,
            style = style.copy(
                color = Color.DarkGray
            )
        )
        Text(
            text = text,
            style = style
        )
    }

}