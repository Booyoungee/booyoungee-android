package com.eoyeongbooyeong.domain

data class Place(
    val name: String = "협재협곡중",
    val address: String = "부산광역시 서구 승학로",
    val star: Float = 4.3f,
    val reviewCount: Int = 100,
    val imageUrl: String = "https://oaidalleapiprodscus.blob.core.windows.net/private/org-TvAP2xDm5O0OclqUH76CW25d/user-MS0cMdfRzAjZjcT2DMzZQbgJ/img-hI72ghAJtRyiNoPJuq7fpJd9.png?st=2024-08-19T14%3A40%3A38Z&se=2024-08-19T16%3A40%3A38Z&sp=r&sv=2024-08-04&sr=b&rscd=inline&rsct=image/png&skoid=d505667d-d6c1-4a0a-bac7-5c84a87759f8&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-08-19T03%3A12%3A40Z&ske=2024-08-20T03%3A12%3A40Z&sks=b&skv=2024-08-04&sig=zj4e988rPE84bb3DcyH9Vx7DaeiyKMYliC1ndj5/RyA%3D",
    val likedCount: Int = 88,
    val movieNameList: List<String> = listOf("영화1", "영화2"),
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
)
