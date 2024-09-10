package com.eoyeongbooyeong.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.eoyeongbooyeong.core.designsystem.component.textfield.BooSearchTextField
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.core.extension.noRippleClickable

@Composable
internal fun SearchRoute(
    navigateUp: () -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    SearchSideEffect.NavigateUp -> navigateUp()
                }
            }
    }

    SearchScreen(
        navigateUp = viewModel::navigateUp,
    )
}

@Composable
private fun SearchScreen(
    navigateUp: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .background(White)
            .fillMaxSize(),
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
        ) {
            Icon(
                painter = painterResource(id = com.eoyeongbooyeong.core.R.drawable.ic_left),
                contentDescription = "back button",
                modifier =
                Modifier
                    .noRippleClickable(navigateUp)
                    .align(Alignment.CenterVertically),
            )
            Spacer(modifier = Modifier.width(6.dp))
            BooSearchTextField(
                text = "",
                onValueChange = {},
                isActive = true,
                onClick = { },
                modifier = Modifier.weight(1f),
            )
        }

        /*
        3개의 state가 필요함
        1. 지금 핫한 여행지
        2. 검색 결과
        3. empty 화면
         */
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    BooTheme {
        SearchScreen()
    }
}
