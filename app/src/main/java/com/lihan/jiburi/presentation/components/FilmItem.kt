package com.lihan.jiburi.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.lihan.jiburi.R
import com.lihan.jiburi.domain.model.Film
import com.lihan.jiburi.ui.theme.JiburiTheme


@Composable
@Preview
private fun FilmItemPreview(){
    JiburiTheme {
        FilmItem(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.White)
            ,
            film = Film(
                id = "234wer",
                title = "天空の城ラピュタ",
                titleEn = "Castle in the Sky",
                titleRoma = "Tenkū no shiro Rapyuta",
                imgUrl = "imgUrl",
                imgUrlBanner = "urlBanner",
                description = "description",
                director = "director",
                producer = "producer",
                releaseDate = "1986",
                score = "95",
                runningTime = "124Mins"
            )
        )
    }
}

@Composable
fun FilmItem(
    modifier: Modifier = Modifier,
    film: Film
){
    Column(
        modifier = modifier
    ){
        SubcomposeAsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .aspectRatio(3 / 4f)
                .clip(RoundedCornerShape(15.dp))
            ,
            model = film.imgUrl,
            contentDescription = stringResource(R.string.filmItemImage),
            loading = {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp)
                    )
                }
            },
            error = {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.cant_find_img),
                        contentDescription = stringResource(R.string.can_not_find_img)
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = "${film.title}(${film.releaseDate})",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = film.titleEn,
                style = MaterialTheme.typography.titleSmall
            )

        }
    }

}