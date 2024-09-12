package com.eoyeongbooyeong.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.places.category.CategoryScreenPreview
import com.eoyeongbooyeong.places.category.PlaceCategoryRoute
import com.eoyeongbooyeong.places.category.PlaceCategoryScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navigator: MainNavigator = rememberMainNavigator()
            BooTheme {
                // MainScreen(navigator = navigator)
                PlaceCategoryRoute(placeType = "movie")
            }
        }
    }
}
