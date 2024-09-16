package com.eoyeongbooyeong.core.designsystem.component.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.eoyeongbooyeong.core.designsystem.component.button.BooMiniButton
import com.eoyeongbooyeong.core.designsystem.theme.Blue100
import com.eoyeongbooyeong.core.designsystem.theme.Blue300
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.White

@Composable
fun BooDialog(
    negativeButtonContext: String,
    positiveButtonContext: String,
    onNegativeButtonClicked: () -> Unit,
    onPositiveButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    description: String? = null,
) {
    Dialog(onDismissRequest = onNegativeButtonClicked) {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = White
            ),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                if (title != null) {
                    Text(
                        text = title,
                        textAlign = TextAlign.Center,
                        style = BooTheme.typography.head2,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                if (description != null) {
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = description,
                        textAlign = TextAlign.Center,
                        style = BooTheme.typography.body4,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }

                Row {
                    BooMiniButton(
                        text = negativeButtonContext,
                        modifier = Modifier.weight(1f),
                        onClick = onNegativeButtonClicked,
                        backgroundColor = Blue100,
                        textColor = Blue300
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    BooMiniButton(
                        text = positiveButtonContext,
                        modifier = Modifier.weight(1f),
                        onClick = onPositiveButtonClicked,
                        backgroundColor = Blue300,
                        textColor = White
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun BooDialogPreview() {
    BooDialog(
        negativeButtonContext = "취소",
        positiveButtonContext = "확인",
        onNegativeButtonClicked = {},
        onPositiveButtonClicked = {},
        title = "Title",
        description = "Description"
    )
}
