package com.eoyeongbooyeong.stamp

sealed class StampSideEffect {
    data class ShowToast(val placeName: String) : StampSideEffect()
}
