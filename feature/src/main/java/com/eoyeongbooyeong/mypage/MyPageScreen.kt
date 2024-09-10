package com.eoyeongbooyeong.mypage

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.eoyeongbooyeong.core.constant.PrivacyPolicy
import com.eoyeongbooyeong.core.constant.TermsOfService
import com.eoyeongbooyeong.core.designsystem.component.dialog.BooDialog
import com.eoyeongbooyeong.core.designsystem.component.topbar.BooTextTopAppBar
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Gray100
import com.eoyeongbooyeong.core.designsystem.theme.Gray400
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.core.extension.noRippleClickable
import com.jakewharton.processphoenix.ProcessPhoenix

@Composable
internal fun MyPageRoute(
    paddingValues: PaddingValues,
    navigateToEditNickname: () -> Unit,
    navigateToMyReview: () -> Unit,
    navigateToBookmark: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.sideEffects, lifecycleOwner) {
        viewModel.sideEffects.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    MyPageSideEffect.RestartApp -> ProcessPhoenix.triggerRebirth(context)
                    is MyPageSideEffect.NavigateToWebView -> {
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(sideEffect.url)))
                    }

                    MyPageSideEffect.NavigateToEditNickname -> navigateToEditNickname()
                    MyPageSideEffect.NavigateToMyReview -> navigateToMyReview()
                    MyPageSideEffect.NavigateToBookmark -> navigateToBookmark()
                }
            }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getUserNickname()
    }

    if (state.value.isWithdrawDialogVisible) {
        BooDialog(
            negativeButtonContext = "취소",
            positiveButtonContext = "확인",
            onNegativeButtonClicked = viewModel::controlWithDrawDialog,
            onPositiveButtonClicked = viewModel::withDraw,
            title = "정말로 탈퇴하시겠어요?",
            description = "모든 정보가 지워지고 복구할 수 없어요"
        )
    }

    if (state.value.isLogoutDialogVisible) {
        BooDialog(
            negativeButtonContext = "취소",
            positiveButtonContext = "확인",
            onNegativeButtonClicked = viewModel::controlLogoutDialog,
            onPositiveButtonClicked = viewModel::logout,
            title = "정말로 로그아웃 하시겠어요?",
            description = "더 많은 부산의 매력을 느껴보세요!"
        )
    }

    MyPageScreen(
        paddingValues = paddingValues,
        nickname = state.value.nickname,
        navigateToWebView = viewModel::navigateToWebView,
        navigateToEditNickname = viewModel::navigateToEditNickname,
        navigateToMyReview = viewModel::navigateToMyReview,
        navigateToBookmark = viewModel::navigateToBookmark,
        withDraw = viewModel::controlWithDrawDialog,
        logout = viewModel::controlLogoutDialog,
    )
}

@Composable
internal fun MyPageScreen(
    paddingValues: PaddingValues,
    nickname: String,
    navigateToWebView: (String) -> Unit = {},
    navigateToEditNickname: () -> Unit = {},
    navigateToMyReview: () -> Unit = {},
    navigateToBookmark: () -> Unit = {},
    withDraw: () -> Unit = {},
    logout: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(White)
    ) {
        BooTextTopAppBar(text = "마이페이지")

        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = com.eoyeongbooyeong.core.R.drawable.ic_profile),
                    contentDescription = "profile Image"
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.noRippleClickable(navigateToEditNickname)
                ) {
                    Text(text = nickname, style = BooTheme.typography.head3)
                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(
                        imageVector = ImageVector.vectorResource(id = com.eoyeongbooyeong.core.R.drawable.ic_right),
                        contentDescription = null
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Row {
                Row(
                    modifier = Modifier
                        .noRippleClickable(navigateToMyReview)
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Gray100)
                        .padding(vertical = 11.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = com.eoyeongbooyeong.core.R.drawable.ic_write),
                        contentDescription = "my ReviewInfoEntity"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "내 리뷰",
                        style = BooTheme.typography.caption1
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Row(
                    modifier = Modifier
                        .noRippleClickable(navigateToBookmark)
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Gray100)
                        .padding(vertical = 11.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = com.eoyeongbooyeong.core.R.drawable.ic_bookmark),
                        contentDescription = "my ReviewInfoEntity"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "북마크",
                        style = BooTheme.typography.caption1
                    )

                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            MyPageOptionItem(
                text = "서비스 이용약관",
                onClick = { navigateToWebView(TermsOfService) }
            )
            MyPageOptionItem(
                text = "개인정보 처리방침",
                onClick = { navigateToWebView(PrivacyPolicy) }
            )
            MyPageOptionItem(
                text = "로그아웃",
                onClick = logout
            )
            MyPageOptionItem(
                text = "탈퇴하기",
                onClick = withDraw
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "앱 버전",
                    color = Gray400,
                    style = BooTheme.typography.caption2
                )
                Text(
                    text = "1.0.0",
                    color = Gray400,
                    style = BooTheme.typography.caption2
                )
            }
        }
    }
}

@Composable
fun MyPageOptionItem(
    text: String,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
            .noRippleClickable(onClick = onClick),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text,
            style = BooTheme.typography.body4
        )

        Icon(
            imageVector = ImageVector.vectorResource(id = com.eoyeongbooyeong.core.R.drawable.ic_right),
            contentDescription = "right arrow"
        )
    }
}

@Preview
@Composable
fun MyPageScreenPreview() {
    BooTheme {
        MyPageScreen(
            PaddingValues(10.dp),
            "닉네임",
        )
    }
}
