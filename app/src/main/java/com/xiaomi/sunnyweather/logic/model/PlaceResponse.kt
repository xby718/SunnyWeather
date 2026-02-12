package com.xiaomi.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 * @description:模型,完全按照API返回的结果来设置，包括status与数组place。其中：
 * Place：
 *  "name": "中国 北京市 北京",
 *  "location": {
 *         "lat": 39.904989,
 *         "lng": 116.405285
 *       }
 *   "formatted_address": "中国 北京市 北京",
 * 注意：失败时 API 只返回 {"status":"failed"}，无 place 字段，故 place 设为可空并默认 null。
 * 彩云接口可能返回 "place" 或 "places"，用 @SerializedName 兼容 "places"。
 * @author : xby
 * @email : xuebaoyu@xiaomi.com
 * @date : 2026/2/10
 * Copyright (c) Xiaomi, Inc.
 **/
data class PlaceResponse(
    val status: String,
    @SerializedName("place") val placeFromPlace: List<Place>? = null,
    @SerializedName("places") val placeFromPlaces: List<Place>? = null
) {
    /** 兼容接口返回 place 或 places 两种字段名 */
    val place: List<Place>?
        get() = placeFromPlace?.takeIf { it.isNotEmpty() } ?: placeFromPlaces
}
data class Place(val name: String, val location: Location, @SerializedName("formatted_address") val address: String)
/** API 返回的 lat/lng 为数字，不能用 String */
data class Location(val lng: Double, val lat: Double)