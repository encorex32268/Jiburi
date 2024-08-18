package com.lihan.jiburi.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lihan.jiburi.domain.model.Film
import com.lihan.jiburi.presentation.components.FilmItem
import com.lihan.jiburi.ui.theme.JiburiTheme
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun FilmScreenRoot(
    viewModel: FilmsViewModel = koinViewModel(),
    onGoToDetail: (Film) -> Unit = {},
    onShowError: (String) -> Unit = {}

){
    val state by viewModel.state.collectAsState()
    LaunchedEffect(viewModel){
        viewModel.uiEvent.collectLatest {
            when(it){
                is FilmsUiEvent.ApiError -> {
                    onShowError(it.errorMessage)
                }
            }
        }
    }
    FilmsScreen(
        state = state,
        onItemClick = onGoToDetail,
        onAction = viewModel::onAction
    )

}

@Composable
@Preview(showSystemUi = true)
fun FilmsScreenPreview() {
    JiburiTheme {
        FilmsScreen(
            state = FilmsState(
                items = (0..50).map {
                    Film(
                        id = "id->${it}",
                        title = "Film Title ${it}",
                        titleEn = "Film Title En ${it}",
                        titleRoma = "Film Title Roma ${it}",
                        imgUrl = "imgUrl ${it}",
                        imgUrlBanner = "imgUrlBanner ${it}",
                        description = "description .. ${it}",
                        director = "director${it}",
                        producer = "producer${it}",
                        runningTime = "${100 + it}",
                        releaseDate = "${1999 + it}",
                        score = "${50 + it}"
                    )
                }
            )
        )
    }
}
@Composable
fun FilmsScreen(
    state: FilmsState = FilmsState(),
    onItemClick: (Film) -> Unit = {},
    onAction: (FilmAction) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
       when{
           state.isLoading                           ->{
               CircularProgressIndicator(
                   modifier = Modifier
                       .size(48.dp)
                       .testTag("FilmScreenLoading")
               )
           }
           !state.isLoading && state.items.isEmpty() -> {
               Column(
                   modifier = Modifier.testTag("FilmScreenNoData"),
                   horizontalAlignment = Alignment.CenterHorizontally
               ){
                   Text(text = "No Data , or some error happened")
                   Button(
                       modifier = Modifier.testTag("FilmScreenRetry"),
                       onClick ={
                       onAction(FilmAction.GetData)
                   }) {
                       Text(text = "Retry")
                   }
               }
           }
           else ->{
               LazyVerticalGrid(
                   modifier = Modifier.testTag("FilmScreenList"),
                   columns = GridCells.Fixed(count = 2),
                   verticalArrangement = Arrangement.spacedBy(2.dp),
                   horizontalArrangement = Arrangement.spacedBy(2.dp)
               ) {
                   items(state.items){ item ->
                       FilmItem(
                           modifier = Modifier
                               .fillMaxWidth()
                               .clickable {
                                   onItemClick(item)
                               }
                           ,
                           film = item
                       )
                   }
               }

           }
       }

    }
}

