package com.xiaomi.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 * @description:模型：
 * Place：
 *  "name": "中国 北京市 北京",
 *  "location": {
 *         "lat": 39.904989,
 *         "lng": 116.405285
 *       }
 *   "formatted_address": "中国 北京市 北京",
 * @author : xby
 * @email : xuebaoyu@xiaomi.com
 * @date : 2026/2/10
 * Copyright (c) Xiaomi, Inc.
 **/
data class PlaceResponse(val name : String,val place:List<Place>)           //数组
data class Place(val name: String,val location: Location,@SerializedName("formatted_address") val address: String)
data class Location(val lng: String,val lat: String)