package com.xiaomi.sunnyweather.logic.network

import com.xiaomi.sunnyweather.SunnyWeatherApplication
import com.xiaomi.sunnyweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @description:定义一个接口用于访问“彩云天气城市搜索API”，返回就是数组PlaceResponse（可以自动解析）
 * @author : xby
 * @email : xuebaoyu@xiaomi.com
 * @date : 2026/2/10
 * Copyright (c) Xiaomi, Inc.
 **/
interface PlaceService {
    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlace(@Query("query") query: String): Call<PlaceResponse>
}