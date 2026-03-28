package com.lihan.jiburi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
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
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.White.toArgb() , Color.Black.toArgb())
        )
        setContent {
            JiburiTheme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.White,
                ) { it ->
                    NavHost(
                        modifier = Modifier.fillMaxSize(),
                        navController = navController,
                        startDestination = FilmListRoute
                    ){
                        composable<FilmListRoute>{
                            FilmScreenRoot(
                                onGoToDetail = { id ->
                                    navController.navigate(
                                        route = FilmDetailRoute(id = id)
                                    )
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
