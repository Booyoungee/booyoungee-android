package com.eoyeongbooyeong.core.designsystem.component.button.radiobutton

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.R
import com.eoyeongbooyeong.core.designsystem.theme.Blue100
import com.eoyeongbooyeong.core.designsystem.theme.Blue300
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.core.extension.noRippleClickable

@Composable
fun BooRadioButton(
    text: String,
    onClicked: () -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean = true,
    textStyle: TextStyle = TextStyle.Default,
) {
    Row(
        modifier = modifier
            .run {
                if (selected) border(1.dp, Blue300, RoundedCornerShape(10.dp))
                else this
            }
            .clip(RoundedCornerShape(10.dp))
            .background(if (selected) Blue100 else White)
            .padding(horizontal = 15.dp, vertical = 17.dp)
            .noRippleClickable(onClick = onClicked),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(
                id = if (selected) R.drawable.ic_check_filled else R.drawable.ic_check_empty
            ),
            modifier = Modifier.size(16.dp),
            tint = Color.Unspecified,
            contentDescription = ""
        )

        Spacer(modifier = Modifier.width(9.dp))

        Text(
            text = text,
            modifier = Modifier.weight(1f, false),
            style = textStyle
        )
    }
}

@Preview
@Composable
fun BooRadioButtonPreview() {
    Column {
        BooRadioButton(text = "전체 동의", onClicked = {}, modifier = Modifier.fillMaxWidth(), selected = false)
        Spacer(modifier = Modifier.height(22.dp))
        BooRadioButton(text = "전체 동의", onClicked = {}, modifier = Modifier.fillMaxWidth(), selected = true)
    }

}
