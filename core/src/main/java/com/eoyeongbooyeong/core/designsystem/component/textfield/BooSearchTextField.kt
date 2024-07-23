package com.eoyeongbooyeong.core.designsystem.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.R
import com.eoyeongbooyeong.core.designsystem.theme.Gray300
import com.eoyeongbooyeong.core.designsystem.theme.Purple
import com.eoyeongbooyeong.core.designsystem.theme.White

@Composable
fun BooSearchTextField(
    text: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
) {
    Row(
        modifier = modifier
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    colorStops = arrayOf(
                        0.0f to Purple,
                        1f to Color(0xFF4067E5)
                    )
                ),
                shape = RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))
            .background(White)
            .padding(horizontal = 20.dp, vertical = 11.dp)
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            decorationBox = { innerTextField ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.weight(1f)) {
                        innerTextField()
                        if (text.isEmpty()) {
                            Text(
                                text = "영화 제목으로 위치를 검색해보세요!",
                                color = Gray300,
                            )
                        }
                    }
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                        contentDescription = null
                    )
                }
            }
        )
    }
}

@Preview
@Composable
fun BooSearchTextFieldPreview() {
    Column {
        BooSearchTextField(text = "")
        Spacer(modifier = Modifier.height(20.dp))
        BooSearchTextField(text = "Search")
    }
}
