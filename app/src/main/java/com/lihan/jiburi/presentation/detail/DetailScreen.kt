@file:OptIn(ExperimentalLayoutApi::class)

package com.lihan.jiburi.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.lihan.jiburi.R
import com.lihan.jiburi.domain.model.Film
import com.lihan.jiburi.ui.MovieIcon
import com.lihan.jiburi.ui.StarIcon
import com.lihan.jiburi.ui.theme.JiburiTheme

@Composable
fun DetailScreenRoot(
    film: Film,
    navController: NavController
) {
    DetailScreen(
        film = film,
        onGoBack = {
            navController.popBackStack()
        }
    )
}

@Composable
@Preview(showSystemUi = true)
fun DetailScreenPreview() {
    JiburiTheme {
        DetailScreen(
            film = Film(
                id = "idTest",
                title = "Test234",
                titleEn = "Film Title En ",
                titleRoma = "Film Title Roma ",
                imgUrl = "imgUrl ",
                imgUrlBanner = "imgUrlBanner ",
                description = "description .. ",
                director = "director",
                producer = "producer",
                runningTime = "200",
                releaseDate = "1983",
                score = "50"
            )
        )
    }
}

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    film: Film,
    onGoBack: () -> Unit = {}
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ){
        IconButton(
            onClick = onGoBack) {
            Icon(
                modifier = Modifier.size(36.dp),
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                contentDescription = stringResource(R.string.go_back_button)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(32.dp)
                )
        ){
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                ,
                model = film.imgUrl,
                contentDescription = stringResource(id = R.string.filmItemImage),
                loading = {
                    Image(
                        painter = painterResource(id = R.drawable.preview_img),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )
                },
                contentScale = ContentScale.FillBounds
            )

        }
        TitleSection(film = film)

        Row {

        }
    }

}
@Composable
private fun TitleSection(
    film: Film
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ){
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ){
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = "${film.title}(${film.releaseDate})",
                style = MaterialTheme.typography.titleLarge
            )
            Row(
                modifier = Modifier.align(Alignment.CenterVertically),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        painter =StarIcon,
                        contentDescription = stringResource(R.string.detail_score),
                        tint = Color.Yellow
                    )
                    Text(
                        text = film.score,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        painter = MovieIcon,
                        contentDescription = stringResource(R.string.detail_score),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "${film.runningTime} minutes",
                        style = MaterialTheme.typography.titleSmall
                    )
                }

            }

        }
        Text(
            text = film.titleRoma,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = film.titleEn,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "director ${film.director}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text ="producer: ${film.producer}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = film.description,
            style = MaterialTheme.typography.bodyMedium
        )
    }

}

@Composable
private fun ScoreSection(){

}