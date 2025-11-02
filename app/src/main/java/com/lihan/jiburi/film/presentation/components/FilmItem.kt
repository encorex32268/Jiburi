package com.lihan.jiburi.film.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.lihan.jiburi.R
import com.lihan.jiburi.film.domain.model.Film
import com.lihan.jiburi.ui.theme.JiburiTheme


@Composable
fun FilmItem(
    modifier: Modifier = Modifier,
    film: Film
){
    Box(
        modifier = modifier
    ){
        SubcomposeAsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(300.dp)
                .clip(RoundedCornerShape(15.dp))
            ,
            model = film.imgUrlBanner,
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
                        painter = painterResource(id = R.drawable.cant_find_img),
                        contentDescription = stringResource(R.string.can_not_find_img)
                    )
                }
            },
            contentScale = ContentScale.FillHeight
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp),
        ){
            ShadowText(
                text =  film.title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.background
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            ShadowText(
                text = film.titleEn,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.background
                )
            )
        }

    }

}



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