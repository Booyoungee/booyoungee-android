package com.eoyeongbooyeong.core.extension

fun List<String>?.listToBracketedString(): String = this?.joinToString(", ") { "<$it>" } ?: "[]"

fun formatReviewDate(dateString: String): String {
    // 앞의 10글자만 가져옴 (연.월.일 부분)
    return dateString.substring(0, 10)
}