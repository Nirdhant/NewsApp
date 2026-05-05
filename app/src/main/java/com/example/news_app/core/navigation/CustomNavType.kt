package com.example.news_app.core.navigation

import android.net.Uri
import androidx.navigation.NavType
import androidx.savedstate.SavedState
import com.example.news_app.news.presentation.news_list.model.NewsUI
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {
    val news = object : NavType<NewsUI>(isNullableAllowed = false){
        override fun put(bundle: SavedState, key: String, value: NewsUI) {
            bundle.putString(key, Json.encodeToString(value))
        }

        override fun get(bundle: SavedState, key: String): NewsUI? {
            return Json.decodeFromString(bundle.getString(key)?: return null)
        }

        override fun parseValue(value: String): NewsUI {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: NewsUI): String {
            //Url will convert in a format that does not contain any = ,+ like operator and use % or sequence of char
            return Uri.encode(Json.encodeToString(value))
        }

    }
}