package com.xiaomi.sunnyweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * @description:全局context
 * @author : xby
 * @email : xuebaoyu@xiaomi.com
 * @date : 2026/2/10
 * Copyright (c) Xiaomi, Inc.
 **/
class SunnyWeatherApplication: Application() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        const val TOKEN = "UYbgMHe4WC2i2OVz"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}