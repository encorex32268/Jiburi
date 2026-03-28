@file:OptIn(ExperimentalMaterial3Api::class)

package com.lihan.jiburi.film.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lihan.jiburi.core.presentation.uitl.ObserveEvent
import com.lihan.jiburi.film.domain.model.Film
import com.lihan.jiburi.film.presentation.components.FilmItem
import com.lihan.jiburi.ui.theme.JiburiTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun FilmScreenRoot(
    viewModel: FilmsViewModel = koinViewModel(),
    onGoToDetail: (id: String) -> Unit = {}
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    val scope = rememberCoroutineScope()
    val snackbarState = remember { SnackbarHostState() }

    ObserveEvent(viewModel.uiEvent) { uiEvent ->
        when(uiEvent){
            is FilmsUiEvent.ApiError -> {
                scope.launch {
                    snackbarState.showSnackbar(uiEvent.errorMessage)
                }
            }
        }
    }

    FilmsScreen(
        state = state,
        onItemClick = onGoToDetail,
        onAction = viewModel::onAction,
        snackbarState = snackbarState
    )

}

@Composable
fun FilmsScreen(
    state: FilmsState,
    snackbarState: SnackbarHostState,
    onItemClick: (id: String) -> Unit = {},
    onAction: (FilmsAction) -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "スタジオジブリ作品",
                        style = MaterialTheme.typography.displaySmall
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                )
            )
        },
        containerColor = Color.White,
        snackbarHost = { SnackbarHost(snackbarState) }
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding()),
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
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ){
                        Text(text = "No Data , or some error happened")
                        Button(
                            modifier = Modifier.testTag("FilmScreenRetry"),
                            onClick ={
                                onAction(FilmsAction.ReloadData)
                            }) {
                            Text(text = "Retry")
                        }
                    }
                }
                else ->{
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .testTag("FilmScreenList"),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        items(state.items){ item ->
                            FilmItem(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onItemClick(item.id)
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
}




@Composable
@Preview(showSystemUi = true)
fun FilmsScreenPreview() {
    JiburiTheme {
        FilmsScreen(
            snackbarState = remember { SnackbarHostState() },
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