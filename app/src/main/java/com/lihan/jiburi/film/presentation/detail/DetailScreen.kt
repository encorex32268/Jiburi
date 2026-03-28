@file:OptIn(ExperimentalLayoutApi::class)

package com.lihan.jiburi.film.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.SubcomposeAsyncImage
import com.lihan.jiburi.R
import com.lihan.jiburi.film.domain.model.Film
import com.lihan.jiburi.film.presentation.detail.components.DescriptionSection
import com.lihan.jiburi.ui.theme.JiburiTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreenRoot(
    viewModel: DetailViewModel = koinViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    DetailScreen(
        state = state,
        onAction = { action ->
            when(action){
                DetailAction.OnBack -> onBack()
            }
        }
    )
}

@Composable
fun DetailScreen(
    state: DetailState,
    onAction: (DetailAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
        ){
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.70f),
                model = state.film?.imgUrl?.ifEmpty { R.drawable.preview_image },
                contentDescription = stringResource(id = R.string.filmItemImage),
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.Center)

                    )
                },
                contentScale = ContentScale.Crop,
            )
            IconButton(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(16.dp).align(Alignment.TopStart),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.White
                ),
                onClick = {
                    onAction(DetailAction.OnBack)
                }
            ) {
                Icon(
                    modifier = Modifier.size(36.dp),
                    imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                    contentDescription = stringResource(R.string.go_back_button)
                )
            }

        }
        if (state.film != null){
            DescriptionSection(
                film = state.film
            )
        }
    }

}



@Composable
@Preview(showSystemUi = true)
fun DetailScreenPreview() {
    JiburiTheme {
        DetailScreen(
            state = DetailState(
                film = Film(
                    id = "idTest",
                    title = "Test234",
                    titleEn = "Film Title En ",
                    titleRoma = "Film Title Roma ",
                    imgUrl = "",
                    imgUrlBanner = "imgUrlBanner ",
                    description = "description .. ",
                    director = "director",
                    producer = "producer",
                    runningTime = "200",
                    releaseDate = "1983",
                    score = "50"
                )
            ),
            onAction = {}
        )
    }
}