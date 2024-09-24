import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.eoyeongbooyeong.core.designsystem.theme.BackgroundBlue
import com.eoyeongbooyeong.core.designsystem.theme.Black
import com.eoyeongbooyeong.core.designsystem.theme.Blue400
import com.eoyeongbooyeong.core.designsystem.theme.BooTheme
import com.eoyeongbooyeong.core.designsystem.theme.Gray400
import com.eoyeongbooyeong.core.designsystem.theme.White
import com.eoyeongbooyeong.core.extension.noRippleClickable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortingBottomSheet(
    onSortSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("별점순") }

    val options = listOf("별점순", "리뷰 많은 순", "좋아요 많은 순")
    val filterMapping = mapOf(
        "별점순" to "star",
        "리뷰 많은 순" to "review",
        "좋아요 많은 순" to "like"
    )

    Column(
        modifier = modifier.background(White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 24.dp)
                .fillMaxWidth()
                .background(White)
        ) {
            Text(
                text = selectedOption,
                modifier = Modifier
                    .noRippleClickable { expanded = true }
                    .padding(8.dp),
                color = Gray400,
                style = BooTheme.typography.body3,
            )
            Icon(
                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = null,
                tint = Gray400,
                modifier = Modifier.noRippleClickable { expanded = true },
            )
        }

        if (expanded) {
            ModalBottomSheet(
                onDismissRequest = { expanded = false },
                sheetState = rememberModalBottomSheetState(
                    skipPartiallyExpanded = true
                ),
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                containerColor = White
            ) {
                // Sheet content
                Column(
                    modifier = Modifier
                        .background(White)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "필터",
                        style = BooTheme.typography.body1,
                        color = Black,
                        modifier = Modifier.padding(vertical = 12.dp).align(Alignment.CenterHorizontally)
                    )
                    options.forEach { option ->
                        Text(
                            text = option,
                            modifier = Modifier
                                .fillMaxWidth()
                                .noRippleClickable {
                                    selectedOption = option
                                    expanded = false
                                    onSortSelected(filterMapping[option] ?: "star")
                                }.background(
                                    if(selectedOption == option) {
                                        BackgroundBlue.copy(alpha = 0.5f)
                                    } else {
                                        White
                                    }
                                ).padding(vertical = 18.dp, horizontal = 16.dp),
                            style =
                            if(selectedOption == option) {
                                BooTheme.typography.body3
                            } else {
                                BooTheme.typography.body4
                            },
                            color = Black
                        )
                    }
                }
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
            SortingBottomSheet(onSortSelected = { Log.d("SortingDropdownMenu", it) })
        }
    }
}
