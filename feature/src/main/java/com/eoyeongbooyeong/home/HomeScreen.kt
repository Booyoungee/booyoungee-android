package com.eoyeongbooyeong.home

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.eoyeongbooyeong.core.R
import com.eoyeongbooyeong.core.designsystem.component.textfield.BooSearchTextField
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView
import com.kakao.vectormap.label.Label
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles

@Composable
internal fun HomeScreen() {
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
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                isActive = false,
                onClick = {}, // navigate to search screen
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
                    override fun onMapReady(map: KakaoMap) {

                        // 마커 위치 더미 데이터
                        val markerLocations = listOf(
                            LatLng.from(37.5665, 126.9780),
                            LatLng.from(37.5700, 126.9768),
                            LatLng.from(37.5625, 126.9823)
                        )

                        markerLocations.map { setMapMarker(map, it) }
                        markerClickListener(map)

                    }

                    private fun setMapMarker(
                        map: KakaoMap,
                        location: LatLng,
                    ): Label {
                        val label = map.labelManager?.layer
                        val styles = LabelStyles.from(LabelStyle.from(R.drawable.ic_marker_36))
                        val labelOptions = LabelOptions.from(location).setStyles(styles)

                        return label?.addLabel(labelOptions) ?: error("Label creation failed")
                    }

                    private fun markerClickListener(map: KakaoMap) {
                        val markerStateMap = mutableMapOf<Label, Boolean>()

                        map.setOnLabelClickListener { _, _, label ->
                            val currentStyle = markerStateMap[label] ?: false
                            val newStyleResId = if (currentStyle) R.drawable.ic_marker_36 else R.drawable.ic_marker_72

                            label.changeStyles(
                                LabelStyles.from(LabelStyle.from(newStyleResId))
                            )

                            markerStateMap[label] = !currentStyle

                            true
                        }
                    }
                }
            )
        }
    }
    return mapView
}