package com.eoyeongbooyeong.core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.eoyeongbooyeong.core.R

val pretendardSemiBold = FontFamily(Font(R.font.pretendard_semibold, FontWeight.SemiBold))
val pretendardRegular = FontFamily(Font(R.font.pretendard_regular, FontWeight.Normal))


@Stable
class BooTypography internal constructor(
    head1: TextStyle,
    head2: TextStyle,
    head3: TextStyle,
    body1: TextStyle,
    body2: TextStyle,
    body3: TextStyle,
    body4: TextStyle,
    caption1: TextStyle,
    caption2: TextStyle,
    caption3: TextStyle,
    caption4: TextStyle,
) {
    var head1: TextStyle by mutableStateOf(head1)
        private set
    var head2: TextStyle by mutableStateOf(head2)
        private set
    var head3: TextStyle by mutableStateOf(head3)
        private set
    var body1: TextStyle by mutableStateOf(body1)
        private set
    var body3: TextStyle by mutableStateOf(body3)
        private set
    var body2: TextStyle by mutableStateOf(body2)
        private set
    var body4: TextStyle by mutableStateOf(body4)
        private set
    var caption1: TextStyle by mutableStateOf(caption1)
        private set
    var caption2: TextStyle by mutableStateOf(caption2)
        private set
    var caption3: TextStyle by mutableStateOf(caption3)
        private set
    var caption4: TextStyle by mutableStateOf(caption4)
        private set

    // Used when changing required properties
    fun copy(
        head1: TextStyle = this.head1,
        head2: TextStyle = this.head2,
        head3: TextStyle = this.head3,
        body1: TextStyle = this.body1,
        body2: TextStyle = this.body2,
        body3: TextStyle = this.body3,
        body4: TextStyle = this.body4,
        caption1: TextStyle = this.caption1,
        caption2: TextStyle = this.caption2,
        caption3: TextStyle = this.caption3,
        caption4: TextStyle = this.caption4,
    ): BooTypography = BooTypography(
        head1,
        head2,
        head3,
        body1,
        body2,
        body3,
        body4,
        caption1,
        caption2,
        caption3,
        caption4
    )

    // Used when updating multiple properties
    fun update(other: BooTypography) {
        head1 = other.head1
        head2 = other.head2
        head3 = other.head3
        body1 = other.body1
        body2 = other.body2
        body3 = other.body3
        body4 = other.body4
        caption1 = other.caption1
        caption2 = other.caption2
        caption3 = other.caption3
        caption4 = other.caption4
    }
}

fun booTextStyle(
    fontFamily: FontFamily,
    fontWeight: FontWeight = FontWeight.Normal, // TODO : Weight?
    fontSize: TextUnit,
    lineHeight: TextUnit,
): TextStyle = TextStyle(
    fontFamily = fontFamily,
    fontWeight = fontWeight,
    fontSize = fontSize,
    lineHeight = lineHeight,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)

@Composable
fun booTypography(): BooTypography {
    return BooTypography(
        head1 = booTextStyle(
            fontFamily = pretendardSemiBold,
            fontSize = 22.sp,
            lineHeight = 33.sp
        ),
        head2 = booTextStyle(
            fontFamily = pretendardSemiBold,
            fontSize = 20.sp,
            lineHeight = 30.sp
        ),
        head3 = booTextStyle(
            fontFamily = pretendardSemiBold,
            fontSize = 18.sp,
            lineHeight = 27.sp
        ),
        body1 = booTextStyle(
            fontFamily = pretendardSemiBold,
            fontSize = 16.sp,
            lineHeight = 24.sp
        ),
        body2 = booTextStyle(
            fontFamily = pretendardRegular,
            fontSize = 16.sp,
            lineHeight = 24.sp
        ),
        body3 = booTextStyle(
            fontFamily = pretendardSemiBold,
            fontSize = 14.sp,
            lineHeight = 21.sp
        ),
        body4 = booTextStyle(
            fontFamily = pretendardRegular,
            fontSize = 14.sp,
            lineHeight = 21.sp
        ),
        caption1 = booTextStyle(
            fontFamily = pretendardSemiBold,
            fontSize = 12.sp,
            lineHeight = 18.sp
        ),
        caption2 = booTextStyle(
            fontFamily = pretendardRegular,
            fontSize = 12.sp,
            lineHeight = 18.sp
        ),
        caption3 = booTextStyle(
            fontFamily = pretendardSemiBold,
            fontSize = 10.sp,
            lineHeight = 15.sp
        ),
        caption4 = booTextStyle(
            fontFamily = pretendardRegular,
            fontSize = 10.sp,
            lineHeight = 15.sp
        )
    )
}