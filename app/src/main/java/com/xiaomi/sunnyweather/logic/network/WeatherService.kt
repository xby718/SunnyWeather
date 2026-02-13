package com.xiaomi.sunnyweather.logic.network

import com.xiaomi.sunnyweather.SunnyWeatherApplication
import com.xiaomi.sunnyweather.logic.model.DailyResponse
import com.xiaomi.sunnyweather.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @description:
 * @author : xby
 * @email : xuebaoyu@xiaomi.com
 * @date : 2026/2/13
 * Copyright (c) Xiaomi, Inc.
 **/
interface WeatherService {

    @GET("v2.6/${SunnyWeatherApplication.TOKEN}/{lng},{lat}}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng: String,@Path("lat") lat: String): Call<RealtimeResponse>

    @GET("v2.6/${SunnyWeatherApplication.TOKEN}/{lng},{lat}}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String,@Path("lat") lat: String):Call<DailyResponse>
}