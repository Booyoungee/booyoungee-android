import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Gray400
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.core.extension.noRippleClickable

@Composable
fun SortingDropdownMenu(
    onSortSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("별점순") }

    val options = listOf("별점순", "리뷰 많은 순", "좋아요 많은 순")

    Box(modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp), contentAlignment = Alignment.CenterEnd) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        ) {
            Text(
                text = selectedOption,
                modifier =
                    Modifier
                        .noRippleClickable { expanded = true }
                        .padding(8.dp),
                color = Gray400,
                style = BooTheme.typography.caption1,
            )
            Icon(
                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = null,
                tint = Gray400,
                modifier = Modifier.noRippleClickable { expanded = true },
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(White).align(Alignment.CenterEnd),
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        selectedOption = option
                        expanded = false
                        onSortSelected(option)
                    },
                    modifier = Modifier.background(White).align(Alignment.End),
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewDropdownMenuComponent() {
    BooTheme {
        Column(
            modifier = Modifier.fillMaxSize().background(White),
        ) {
            SortingDropdownMenu(onSortSelected = { Log.d("SortingDropdownMenu", it) })
        }
    }
}
