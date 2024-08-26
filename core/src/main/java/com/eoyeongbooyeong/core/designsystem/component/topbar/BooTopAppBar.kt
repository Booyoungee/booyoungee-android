package com.eoyeongbooyeong.core.designsystem.component.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.R
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.White

@Composable
fun BooTopAppBar(
    modifier: Modifier = Modifier,
    leadingIcon: @Composable () -> Unit = {},
    content: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(White)
            .padding(horizontal = 24.dp)
            .padding(top = 17.dp, bottom = 24.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            leadingIcon()
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            trailingIcon()
        }
    }
}

@Composable
fun BooTextTopAppBar(
    text: String,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable () -> Unit = {},
) {
    BooTopAppBar(
        modifier = modifier,
        leadingIcon = leadingIcon,
        content = {
            Text(text = text, style = BooTheme.typography.body1)
        }
    )
}

@Preview
@Composable
fun BooTopAppBarPreview() {
    BooTheme {
        Column {
            BooTextTopAppBar(
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_left),
                        contentDescription = "left"
                    )
                },
                text = "닉네임 변경",
            )

            HorizontalDivider()

            BooTextTopAppBar(
                text = "마이페이지",
            )

        }
    }
}
