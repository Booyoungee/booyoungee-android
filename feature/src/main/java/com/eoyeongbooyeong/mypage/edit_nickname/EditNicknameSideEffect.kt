package com.eoyeongbooyeong.mypage.edit_nickname

sealed class EditNicknameSideEffect {
    data object NavigateUp : EditNicknameSideEffect()
}
