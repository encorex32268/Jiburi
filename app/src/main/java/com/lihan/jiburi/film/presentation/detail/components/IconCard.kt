package com.lihan.jiburi.film.presentation.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lihan.jiburi.R
import com.lihan.jiburi.ui.theme.JiburiTheme

@Composable
fun IconCard(
    text: String,
    imageVector: ImageVector,
    tintColor: Color = Color.Unspecified,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color.LightGray.copy(alpha = 0.2f))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ){
        Icon(
            imageVector = imageVector,
            contentDescription = "Icon",
            tint = tintColor
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun IconCardPreview() {
    JiburiTheme {
        IconCard(
            text = "121mins",
            imageVector = ImageVector.vectorResource(R.drawable.clock)
        )
    }
}