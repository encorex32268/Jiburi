package com.lihan.jiburi.presentation.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.lihan.jiburi.domain.model.Film
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object FilmNavType  {

    val FilmType = object : NavType<Film>(
        isNullableAllowed = false
    ){
        override fun get(bundle: Bundle, key: String): Film? {
            return Json.decodeFromString(bundle.getString(key)?:return null)
        }

        override fun parseValue(value: String): Film {
           return Json.decodeFromString(Uri.decode(value))
        }

        override fun put(bundle: Bundle, key: String, value: Film) {
            bundle.putString(key,Json.encodeToString(value))
        }

        override fun serializeAsValue(value: Film): String {
            return Uri.encode(Json.encodeToString(value))
        }

    }
}