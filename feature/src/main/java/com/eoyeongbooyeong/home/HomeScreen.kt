package com.eoyeongbooyeong.home

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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.eoyeongbooyeong.core.R
import com.eoyeongbooyeong.core.designsystem.component.textfield.BooSearchTextField
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.domain.Place
import com.eoyeongbooyeong.home.component.FloatingButton
import com.eoyeongbooyeong.home.component.HomeFloatingButton
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

@Composable
internal fun HomeScreen(onClickBookmark: () -> Unit = {}) {
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
    val selectedPlace = remember { mutableStateOf<Place?>(null) }

    Scaffold(
        modifier =
        Modifier
            .fillMaxSize()
            .systemBarsPadding(),
    ) { paddingValue ->
        Column(
            modifier =
            Modifier
                .fillMaxSize()
                .background(color = White),
        ) {
            BooSearchTextField(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                isActive = false,
                onClick = {}, // navigate to search screen
            )
            Box(
                modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValue),
            ) {
                AndroidView(
                    factory = {
                        mapView
                    },
                )

                Column(
                    modifier =
                    Modifier
                        .align(Alignment.BottomEnd),
                ) {
                    HomeFloatingButton(
                        modifier =
                        Modifier
                            .padding(end = 24.dp, bottom = 12.dp),
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

                    HomeFloatingButton(
                        modifier =
                        Modifier
                            .padding(end = 24.dp, bottom = 24.dp),
                        onClick = {
                            // Toggle PlaceInfoBox visibility
                            selectedPlace.value =
                                Place(
                                    name = "Example Place",
                                    address = "123 Example Street 123 Example Street 123 Example Street 123 Example Street",
                                    star = 4.5f,
                                    reviewCount = 42,
                                    likedCount = 15,
                                    movieNameList =
                                    listOf(
                                        "Movie A",
                                        "Movie B",
                                        "Movie B",
                                        "Movie B",
                                    ),
                                    imageUrl = "https://example.com/image.jpg", // Provide a valid image URL
                                )
                            showPlaceInfoBox.value = !showPlaceInfoBox.value
                        },
                        buttonState = FloatingButton(isBookmarkButton = true),
                    )
                }

                if (showPlaceInfoBox.value && selectedPlace.value != null) {
                    PlaceInfoBox(
                        place = selectedPlace.value!!,
                        modifier =
                        Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                    )
                }
            }
        }
    }
}

@Composable
fun rememberMapView(
    context: Context,
    onMapReady: (KakaoMap) -> Unit,
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
                        override fun getPosition(): LatLng = LatLng.from(35.16001944, 129.1658083)

                        override fun getZoomLevel(): Int = 13

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
                                val newStyleResId =
                                    if (currentStyle) R.drawable.ic_marker_36 else R.drawable.ic_marker_72

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
                    val cameraUpdate =
                        CameraUpdateFactory.newCenterPosition(
                            LatLng.from(
                                location.latitude,
                                location.longitude,
                            ),
                        )
                    CameraUpdateFactory.zoomTo(17)
                    kakaoMap.value?.moveCamera(cameraUpdate, CameraAnimation.from(500, true, true))
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
