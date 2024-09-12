package com.eoyeongbooyeong.core.extension

fun List<String>?.listToBracketedString(): String = this?.joinToString(", ") { "<$it>" } ?: "[]"
