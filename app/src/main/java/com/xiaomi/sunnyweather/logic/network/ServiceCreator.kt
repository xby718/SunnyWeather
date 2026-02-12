package com.xiaomi.sunnyweather.logic.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @description:Retrofir构建器，为了使用“能访问彩云天球搜索API的接口”的searchPlace
 * @author : xby
 * @email : xuebaoyu@xiaomi.com
 * @date : 2026/2/10
 * Copyright (c) Xiaomi, Inc.
 **/
object ServiceCreator {
    private const val BASE_URL = "https://api.caiyunapp.com/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
        redactHeader("Authorization")
    }

    //如果不用 OkHttpClient，Retrofit 会用一个默认的 OkHttpClient.
    //定义 okHttpClient 主要是为了加一个日志拦截器，方便调试
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun <T> create(serviceClass: Class<T>):T = retrofit.create(serviceClass)

    inline fun <reified T> create():T = create(T::class.java)
}