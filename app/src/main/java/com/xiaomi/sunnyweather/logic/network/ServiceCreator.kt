package com.xiaomi.sunnyweather.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @description:Retrofir构建器，为了使用“能访问彩云天球搜索API的接口”的searchPlace
 * @author : xby
 * @email : xuebaoyu@xiaomi.com
 * @date : 2026/2/10
 * Copyright (c) Xiaomi, Inc.
 **/
object ServiceCreator {
    private const val BASE_URL = "https://api.caiyunapp.com/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun <T> create(serviceClass: Class<T>):T = retrofit.create(serviceClass)

    inline fun <reified T> create():T = create(T::class.java)
}