package com.eoyeongbooyeong.data.dto.response

import com.eoyeongbooyeong.domain.entity.TourInfoEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TourInfoPlaceDto(
    @SerialName("addr1")
    val addr1: String,
    @SerialName("addr2")
    val addr2: String,
    @SerialName("areacode")
    val areacode: String,
    @SerialName("booktour")
    val booktour: String,
    @SerialName("cat1")
    val cat1: String,
    @SerialName("cat2")
    val cat2: String,
    @SerialName("cat3")
    val cat3: String,
    @SerialName("contentid")
    val contentid: String,
    @SerialName("contenttypeid")
    val contenttypeid: String,
    @SerialName("createdtime")
    val createdtime: String,
    @SerialName("dist")
    val dist: String?,
    @SerialName("firstimage")
    val firstimage: String,
    @SerialName("firstimage2")
    val firstimage2: String,
    @SerialName("cpyrhtDivCd")
    val cpyrhtDivCd: String,
    @SerialName("mapx")
    val mapx: String,
    @SerialName("mapy")
    val mapy: String,
    @SerialName("mlevel")
    val mlevel: String,
    @SerialName("modifiedtime")
    val modifiedtime: String,
    @SerialName("sigungucode")
    val sigungucode: String,
    @SerialName("tel")
    val tel: String,
    @SerialName("title")
    val title: String,
    @SerialName("zipcode")
    val zipcode: String?,
    @SerialName("placeType")
    val placeType: String
) {
    fun toDomain() = TourInfoEntity(
        addr1 = addr1,
        addr2 = addr2,
        areacode = areacode,
        booktour = booktour,
        cat1 = cat1,
        cat2 = cat2,
        cat3 = cat3,
        contentid = contentid,
        contenttypeid = contenttypeid,
        createdtime = createdtime,
        dist = dist,
        firstimage = firstimage,
        firstimage2 = firstimage2,
        cpyrhtDivCd = cpyrhtDivCd,
        mapx = mapx,
        mapy = mapy,
        mlevel = mlevel,
        modifiedtime = modifiedtime,
        sigungucode = sigungucode,
        tel = tel,
        title = title,
        zipcode = zipcode,
        placeType = placeType
    )
}
