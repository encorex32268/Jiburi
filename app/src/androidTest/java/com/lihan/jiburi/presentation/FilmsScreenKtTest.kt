package com.lihan.jiburi.presentation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lihan.jiburi.MainActivity
import com.lihan.jiburi.data.FakeFilmRemoteDataSource
import com.lihan.jiburi.data.FakeFilmsRepositoryImpl
import com.lihan.jiburi.data.FakeLocalFilmDataSource
import com.lihan.jiburi.data.model.FilmDto
import com.lihan.jiburi.domain.model.Film
import com.lihan.jiburi.ui.theme.JiburiTheme
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.compose.koinViewModel

class FilmsScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun checkFilmListScreen(){
        composeTestRule.setContent {
            JiburiTheme {
                FilmsScreen(
                    state = FilmsState(
                        items = listOf(
                            Film(
                                id = "idTest",
                                title = "TitleFromTester",
                                titleEn = "TitleEnFromTester",
                                titleRoma = "TitleRoma",
                                imgUrl = "Url",
                                imgUrlBanner = "UrlBanner",
                                director = "director",
                                producer = "producer",
                                description = "description",
                                releaseDate = "1990",
                                runningTime = "1234",
                                score = "88"
                            )
                        )
                    )
                )
            }
        }
        composeTestRule.onNodeWithTag("FilmScreenList").assertIsDisplayed()
    }


    @Test
    fun checkFilmListScreenLoading(){
        composeTestRule.setContent {
            JiburiTheme {
                FilmsScreen(
                    state = FilmsState(
                        items = listOf(
                        ),
                        isLoading = true
                    )
                )
            }
        }
        composeTestRule.onNodeWithTag("FilmScreenLoading").assertIsDisplayed()
    }

    @Test
    fun checkFilmListScreenNoDataRetry(){
        val fakeFilmRemote = FakeFilmRemoteDataSource().apply {
            isSuccess = false
        }
        val viewModel = FilmsViewModel(
            filmsRepository = FakeFilmsRepositoryImpl(
                filmRemoteDataSource =  fakeFilmRemote,
                localFilmDataSource = FakeLocalFilmDataSource()
            )
        )
        composeTestRule.setContent {
            val state = viewModel.state.collectAsState()
            JiburiTheme {
                FilmsScreen(
                    state = state.value,
                    onAction = viewModel::onAction
                )
            }
        }
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("FilmScreenNoData").assertIsDisplayed()
        fakeFilmRemote.isSuccess = true
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("FilmScreenRetry").performClick()
        composeTestRule.onNodeWithTag("FilmScreenList").assertIsDisplayed()

    }



}