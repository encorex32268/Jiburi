package com.lihan.jiburi.film.presentation.detail.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lihan.jiburi.R
import com.lihan.jiburi.film.domain.model.Film
import com.lihan.jiburi.ui.theme.JiburiTheme

@Composable
fun DescriptionSection(
    film: Film
){
    var isNeedExpand by remember {
        mutableStateOf(false)
    }
    var isExpandDescription by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ){
        //Original_Title
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ){
            Text(
                modifier = Modifier.alignByBaseline(),
                text = film.title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                modifier = Modifier.alignByBaseline(),
                text = film.releaseDate,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
        Text(
            text = film.titleRoma,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Gray
            )
        )
        Text(
            text = film.titleEn,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Gray
            )
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){
            IconCard(
                text = film.runningTime + " minutes",
                imageVector = ImageVector.vectorResource(R.drawable.clock),
                tintColor = MaterialTheme.colorScheme.tertiary
                )
            IconCard(
                text = film.score,
                imageVector = Icons.Outlined.Star,
                tintColor = Color.Yellow
            )
        }
        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .background(Color.LightGray.copy(alpha = 0.2f))
                .padding(8.dp)
            ,
            text = film.description,
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Normal
            ),
            maxLines = if (isExpandDescription) Int.MAX_VALUE else 3,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = {
                isNeedExpand = it.lineCount >= 3
            }
        )
        AnimatedVisibility(isNeedExpand) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ){
                IconButton(
                    onClick = {
                        isExpandDescription = !isExpandDescription
                    }
                ) {
                    Icon(
                        imageVector = if (isExpandDescription){
                            Icons.Default.KeyboardArrowUp
                        }else{
                            Icons.Default.KeyboardArrowDown
                        },
                        contentDescription = null
                    )
                }
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
private fun DescriptionSectionPreview() {
    JiburiTheme {
        DescriptionSection(
            film = Film(
                id = "idTest",
                title = "天空の城ラピュタ",
                titleEn = "Castle in the Sky",
                titleRoma = "Tenkū no shiro Rapyuta",
                imgUrl = "imgUrl ",
                imgUrlBanner = "imgUrlBanner ",
                description = "The orphan Sheeta inherited a mysterious crystal that links her to the mythical sky-kingdom of Laputa. With the help of resourceful Pazu and a rollicking band of sky pirates, she makes her way to the ruins of the once-great civilization. Sheeta and Pazu must outwit the evil Muska, who plans to use Laputa's science to make himself ruler of the world.",
                director = "Hayao Miyazaki",
                producer = "Isao Takahata",
                runningTime = "124",
                releaseDate = "1986",
                score = "95"
            )
        )
    }
    
}
