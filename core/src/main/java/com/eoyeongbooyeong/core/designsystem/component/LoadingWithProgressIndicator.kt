package com.eoyeongbooyeong.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.eoyeongbooyeong.core.designsystem.theme.Purple
import com.eoyeongbooyeong.core.designsystem.theme.White

@Composable
fun LoadingWithProgressIndicator() {
    Box(
        modifier = Modifier.fillMaxSize().background(White.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        androidx.compose.material3.CircularProgressIndicator( color = Purple )
    }
}

@Composable
fun WhiteLoadingWithProgressIndicator() {
    Box(
        modifier = Modifier.fillMaxSize().background(White),
        contentAlignment = Alignment.Center
    ) {
        androidx.compose.material3.CircularProgressIndicator( color = Purple )
    }
}