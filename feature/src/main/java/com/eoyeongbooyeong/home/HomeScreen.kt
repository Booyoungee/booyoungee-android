package com.eoyeongbooyeong.home

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.eoyeongbooyeong.core.designsystem.component.textfield.BooSearchTextField
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView

@Composable
internal fun HomeScreen(
    searchText: String?,
) {

    val context = LocalContext.current
    val mapView = rememberMapView(context = context)

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = White)
        ) {
            BooSearchTextField(
                text = searchText ?: "",
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValue)
            ) {
                AndroidView(
                    factory = {
                        mapView
                    },
                )
            }
        }
    }
}

@Composable
fun rememberMapView(
    context: Context,
): MapView {
    val mapView = remember {
        MapView(context).also { mapView ->
            mapView.start(
                object : MapLifeCycleCallback() {
                    override fun onMapDestroy() = Unit
                    override fun onMapError(e: Exception?) = Unit
                    override fun onMapResumed() = Unit
                },

                object : KakaoMapReadyCallback() {
                    override fun onMapReady(map: KakaoMap) = Unit
                }
            )
        }
    }

    return mapView
}