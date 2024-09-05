package com.eoyeongbooyeong.category.map

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.eoyeongbooyeong.category.component.FloatingButton
import com.eoyeongbooyeong.category.component.MapFloatingButton
import com.eoyeongbooyeong.core.R
import com.eoyeongbooyeong.core.designsystem.component.topbar.BooTextTopAppBar
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.domain.entity.PlaceEntity
import com.eoyeongbooyeong.domain.entity.PlaceType
import com.eoyeongbooyeong.home.component.PlaceInfoBox
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView
import com.kakao.vectormap.camera.CameraAnimation
import com.kakao.vectormap.camera.CameraUpdateFactory
import com.kakao.vectormap.label.Label
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles

private const val BUSAN_STATION_LATITUDE = 35.114495
private const val BUSAN_STATION_LONGTITUDE = 129.03933

@Composable
fun KakaoMapRoute() {
    KakakoMapScreen()
}

@Composable
internal fun KakakoMapScreen() {
    val context = LocalContext.current
    val kakaoMap = remember { mutableStateOf<KakaoMap?>(null) }
    val mapView =
        rememberMapView(context = context, onMapReady = { map ->
            kakaoMap.value = map
        })

    val locationPermissionGranted = remember { mutableStateOf(false) }
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    val requestLocationPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions(),
        ) { permissions ->
            locationPermissionGranted.value =
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
        }

    LaunchedEffect(Unit) {
        val hasFineLocationPermission =
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION,
            ) == PackageManager.PERMISSION_GRANTED
        val hasCoarseLocationPermission =
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            ) == PackageManager.PERMISSION_GRANTED

        locationPermissionGranted.value = hasFineLocationPermission && hasCoarseLocationPermission
    }

    // PlaceInfoBox 에 대한 State
    val showPlaceInfoBox = remember { mutableStateOf(false) }
    val selectedPlaceEntity = remember { mutableStateOf<PlaceEntity?>(null) }

    Column(
        modifier = Modifier.fillMaxSize().background(color = White).statusBarsPadding().systemBarsPadding(),
    ) {
        BooTextTopAppBar(
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_left),
                    contentDescription = "left",
                )
            },
            text = "",
        )

        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            AndroidView(factory = { mapView })

            Column(
                modifier = Modifier.align(Alignment.BottomEnd),
            ) {
                MapFloatingButton(
                    modifier = Modifier.padding(end = 24.dp, bottom = 30.dp),
                    onClick = {
                        requestPermissionAndMoveToCurrentLocation(
                            locationPermissionGranted,
                            fusedLocationClient,
                            kakaoMap,
                            requestLocationPermissionLauncher,
                            context,
                        )
                    },
                    buttonState = FloatingButton(isMyLocationButton = true),
                )
            }

            if (showPlaceInfoBox.value && selectedPlaceEntity.value != null) {
                PlaceInfoBox(
                    placeEntity = selectedPlaceEntity.value!!,
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                )
            }
        }
    }
}

@Composable
fun rememberMapView(
    context: Context,
    onMapReady: (KakaoMap) -> Unit,
    placeType: PlaceType = PlaceType.MOVIE
): MapView {
    val mapView =
        remember {
            MapView(context).also { mapView ->
                mapView.start(
                    object : MapLifeCycleCallback() {
                        override fun onMapDestroy() = Unit

                        override fun onMapError(e: Exception?) = Unit

                        override fun onMapResumed() = Unit
                    },
                    object : KakaoMapReadyCallback() {
                        override fun getPosition(): LatLng = LatLng.from(BUSAN_STATION_LATITUDE, BUSAN_STATION_LONGTITUDE)

                        override fun getZoomLevel(): Int = 17

                        override fun onMapReady(map: KakaoMap) {
                            onMapReady(map) // KakaoMap 객체를 상태로 업데이트합니다.
                            // TODO : 마커 위치 더미 데이터
                            val markerLocations =
                                listOf(
                                    LatLng.from(35.16001944, 129.1658083),
                                    LatLng.from(35.16801944, 129.258083),
                                    LatLng.from(35.15001944, 129.1858083),
                                )

                            markerLocations.map { setMapMarker(map, it) }
                            markerClickListener(map)
                        }

                        val markerIconResId = when (placeType) {
                            PlaceType.MOVIE -> R.drawable.ic_marker_movie
                            PlaceType.LOCAL_SUPPORT -> R.drawable.ic_marker_local_restaurant
                            PlaceType.TOUR -> R.drawable.ic_marker_food
                        }

                        private fun setMapMarker(
                            map: KakaoMap,
                            location: LatLng,
                        ): Label {
                            val label = map.labelManager?.layer

                            val styles = LabelStyles.from(LabelStyle.from(markerIconResId))
                            val labelOptions = LabelOptions.from(location).setStyles(styles)

                            return label?.addLabel(labelOptions) ?: error("Label creation failed")
                        }

                        private fun markerClickListener(map: KakaoMap) {
                            val markerStateMap = mutableMapOf<Label, Boolean>()

                            map.setOnLabelClickListener { _, _, label ->
                                val currentStyle = markerStateMap[label] ?: false
                                val newStyleResId =
                                    if (currentStyle) markerIconResId else R.drawable.ic_marker_clicked_81

                                label.changeStyles(
                                    LabelStyles.from(LabelStyle.from(newStyleResId)),
                                )

                                markerStateMap[label] = !currentStyle
                                true
                            }
                        }
                    },
                )
            }
        }
    return mapView
}

private fun requestPermissionAndMoveToCurrentLocation(
    locationPermissionGranted: MutableState<Boolean>,
    fusedLocationClient: FusedLocationProviderClient,
    kakaoMap: MutableState<KakaoMap?>,
    requestLocationPermissionLauncher: ManagedActivityResultLauncher<Array<String>, Map<String, @JvmSuppressWildcards Boolean>>,
    context: Context,
) {
    if (locationPermissionGranted.value) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION,
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        fusedLocationClient
            .getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                null,
            ).addOnSuccessListener { location ->
                if (location != null) {
                    kakaoMap.value?.moveCameraToLocation(
                        location.latitude,
                        location.longitude,
                    )
                } else {
                    // 위치 정보가 null인 경우 (GPS가 꺼져있는 경우)
                    setGPSPermissionDialog(context)
                }
            }
    } else {
        requestLocationPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            ),
        )
    }
}

private fun setGPSPermissionDialog(context: Context) {
    AlertDialog.Builder(context).apply {
        setTitle(context.getString(com.eoyeongbooyeong.feature.R.string.dialogGPSTitle))
        setMessage(context.getString(com.eoyeongbooyeong.feature.R.string.dialogGPSMessage))
        setPositiveButton(context.getString(com.eoyeongbooyeong.feature.R.string.dialogGPSOk)) { _: DialogInterface, _: Int ->
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            context.startActivity(intent)
        }
        setNegativeButton(context.getString(com.eoyeongbooyeong.feature.R.string.cancel)) { _: DialogInterface, _: Int ->
            Toast
                .makeText(
                    context,
                    context.getString(com.eoyeongbooyeong.feature.R.string.disableGPSPermissionToast),
                    Toast.LENGTH_SHORT,
                ).show()
        }
        create().show()
    }
}

// 화면 이동 확장함수
fun KakaoMap.moveCameraToLocation(
    latitude: Double,
    longitude: Double,
    zoomLevel: Int = 17,
    animationDuration: Int = 500,
) {
    val cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from(latitude, longitude))
    val zoomUpdate = CameraUpdateFactory.zoomTo(zoomLevel)

    moveCamera(cameraUpdate, CameraAnimation.from(animationDuration, true, true))
    moveCamera(zoomUpdate, CameraAnimation.from(animationDuration, true, true))
}