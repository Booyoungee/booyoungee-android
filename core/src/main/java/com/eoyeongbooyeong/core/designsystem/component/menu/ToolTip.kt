package com.eoyeongbooyeong.core.designsystem.component.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.R
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Gray200
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.core.extension.noRippleClickable

@Composable
fun ReviewDropdownMenu(menuItemText: @Composable () -> Unit) {
    var isDropDownMenuExpanded by remember { mutableStateOf(false) }

    Icon(
        painter = painterResource(id = R.drawable.ic_menu),
        contentDescription = "menu icon",
        modifier =
            Modifier
                .wrapContentSize()
                .noRippleClickable { isDropDownMenuExpanded = true },
    )

    DropdownMenu(
        expanded = isDropDownMenuExpanded,
        onDismissRequest = { isDropDownMenuExpanded = false },
        modifier = Modifier.background(White).wrapContentSize(),
    ) {
        DropdownMenuItem(
            onClick = {
                isDropDownMenuExpanded = !isDropDownMenuExpanded
            },
            text = menuItemText,
            modifier = Modifier.border(1.dp, Gray200).background(White),
        )
    }
}

@Composable
@Preview
fun ReviewDropdownMenuPreview() {
    BooTheme {
        Column(
            modifier =
                Modifier
                    .background(White)
                    .fillMaxSize(),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        ) {
            ReviewDropdownMenu {
                Text(text = "신고하기")
            }
        }
    }
}
