package com.lihan.jiburi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lihan.jiburi.core.presentation.navigation.FilmDetailRoute
import com.lihan.jiburi.core.presentation.navigation.FilmListRoute
import com.lihan.jiburi.film.presentation.FilmScreenRoot
import com.lihan.jiburi.film.presentation.detail.DetailScreenRoot
import com.lihan.jiburi.ui.theme.JiburiTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JiburiTheme {
                val scope = rememberCoroutineScope()
                val navController = rememberNavController()
                val snackBarHostState = remember {
                    SnackbarHostState()
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background,
                    snackbarHost = {
                        SnackbarHost(hostState = snackBarHostState)
                    }
                ) { it ->
                    NavHost(
                        navController = navController,
                        startDestination = FilmListRoute
                    ){
                        composable<FilmListRoute>{
                            FilmScreenRoot(
                                onGoToDetail = { id ->
                                    navController.navigate(
                                        route = FilmDetailRoute(id = id)
                                    )
                                },
                                onShowError = {
                                    scope.launch {
                                        snackBarHostState.showSnackbar(
                                            message = it
                                        )
                                    }
                                }
                            )
                        }
                        composable<FilmDetailRoute>{
                            DetailScreenRoot(
                                onBack = {
                                    navController.navigateUp()
                                }
                            )
                        }
                    }

                }
            }
        }
    }
}
