package com.eoyeongbooyeong.core.designsystem.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.R
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Gray100
import com.eoyeongbooyeong.core.designsystem.theme.Gray300

@Composable
fun BooSearchTextField(
    text: String? = null,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
    isActive: Boolean = true,
    onClick: () -> Unit = {},
) {
    Row(
        modifier =
        modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Gray100)
            .padding(horizontal = 20.dp, vertical = 11.dp),
    ) {
        if (isActive) {
            BasicTextField(
                value = text ?: "",
                onValueChange = onValueChange,
                decorationBox = { innerTextField ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.weight(1f)) {
                            innerTextField()
                            if (text?.isEmpty() == true || text == null) {
                                Text(
                                    text = stringResource(R.string.movieSearchBarHint),
                                    color = Gray300,
                                    style = BooTheme.typography.caption2,
                                    maxLines = 1
                                )
                            }
                        }
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                            contentDescription = null,
                        )
                    }
                },
            )
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier =
                    Modifier
                        .weight(1f)
                        .clickable(onClick = onClick),
                ) {
                    if (text?.isEmpty() == true || text == null) {
                        Text(
                            text = stringResource(R.string.movieSearchBarHint),
                            color = Gray300,
                            style = BooTheme.typography.caption2,
                        )
                    }
                }
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                    contentDescription = null,
                )
            }
        }
    }
}

@Preview()
@Composable
fun BooSearchTextFieldPreview() {
    BooTheme {
        Column {
            BooSearchTextField(text = "")
            Spacer(modifier = Modifier.height(20.dp))
            BooSearchTextField(text = "Search")
        }
    }
}
