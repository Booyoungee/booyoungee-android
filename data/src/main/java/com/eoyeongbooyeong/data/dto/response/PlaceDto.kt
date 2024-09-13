package com.eoyeongbooyeong.data.dto.response

import com.eoyeongbooyeong.domain.entity.MeEntity
import com.eoyeongbooyeong.domain.entity.PlaceInfoEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceDto(
    @SerialName("placeId")
    val placeId: String,
    @SerialName("name")
    val name: String,
    @SerialName("address")
    val address: String,
    @SerialName("tel")
    val tel: String?,
    @SerialName("images")
    val images: List<String>,
    @SerialName("type")
    val type: String,
    @SerialName("movies")
    val movies: List<String>?,
    @SerialName("posterUrl")
    val posterUrl: List<String>?,
    @SerialName("likeCount")
    val likeCount: Int,
    @SerialName("starCount")
    val starCount: Int,
    @SerialName("stampCount")
    val stampCount: Int,
    @SerialName("reviewCount")
    val reviewCount: Int,
    @SerialName("bookmarkCount")
    val bookmarkCount: Int,
    @SerialName("stars")
    val stars: Double,
    //@SerialName("mapX")
    //val mapX: String,
    //@SerialName("mapY")
    //val mapY: String,
    @SerialName("me")
    val me: Me,
) {
    @Serializable
    data class Me(
        @SerialName("hasStamp")
        val hasStamp: Boolean,
        @SerialName("hasLike")
        val hasLike: Boolean,
        @SerialName("hasBookmark")
        val hasBookmark: Boolean,
    ) {
        fun toDomain() = MeEntity(
            hasStamp = hasStamp,
            hasLike = hasLike,
            hasBookmark = hasBookmark,
        )
    }

    fun toDomain() = PlaceInfoEntity(
        placeId = placeId,
        name = name,
        address = address,
        tel = tel,
        images = images,
        type = type,
        movies = movies,
        posterUrl = posterUrl,
        likeCount = likeCount,
        starCount = starCount,
        stampCount = stampCount,
        reviewCount = reviewCount,
        stars = String.format("%.1f", stars).toDouble(),
        bookmarkCount = bookmarkCount,
        //mapX = mapX,
        //mapY = mapY,
        me = me.toDomain()
    )
}
