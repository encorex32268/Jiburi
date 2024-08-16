package com.lihan.jiburi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.lihan.jiburi.domain.model.Film
import com.lihan.jiburi.presentation.FilmScreenRoot
import com.lihan.jiburi.presentation.detail.DetailScreenRoot
import com.lihan.jiburi.presentation.navigation.FilmDetailRoute
import com.lihan.jiburi.presentation.navigation.FilmListRoute
import com.lihan.jiburi.presentation.navigation.FilmNavType
import com.lihan.jiburi.ui.theme.JiburiTheme
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            JiburiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = FilmListRoute){
                        composable<FilmListRoute>{
                            FilmScreenRoot(
                                onGoToDetail = {
                                    navController.navigate(
                                        route = FilmDetailRoute(
                                            film = it
                                        )
                                    )
                                }
                            )
                        }
                        composable<FilmDetailRoute>(
                            typeMap = mapOf(
                                typeOf<Film>() to FilmNavType.FilmType
                            )
                        ){
                            val arguments = it.toRoute<FilmDetailRoute>()
                            println("${arguments.film}")
                            DetailScreenRoot(
                                film = arguments.film,
                                navController = navController
                            )
                        }
                    }

                }
            }
        }
    }
}
