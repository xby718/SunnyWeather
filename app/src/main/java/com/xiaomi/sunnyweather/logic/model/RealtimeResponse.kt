package com.xiaomi.sunnyweather.logic.model

import android.health.connect.datatypes.units.Temperature
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.ApiStatus

/**
 * @description:
 * @author : xby
 * @email : xuebaoyu@xiaomi.com
 * @date : 2026/2/13
 * Copyright (c) Xiaomi, Inc.
 **/
data class RealtimeResponse(val stasus : String,val result: Result) {
    data class Result(val realtime : Realtime)

    data class Realtime(val skycon:String,val temperature: Float,@SerializedName("air_quality") val airQuality: AirQuality)

    data class AirQuality(val api: Aqi)

    data class Aqi(val chn: String)
}