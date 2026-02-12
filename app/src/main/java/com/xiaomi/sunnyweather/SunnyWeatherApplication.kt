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
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        /** 彩云天气 API Token，若地点搜索返回失败请到 https://platform.caiyunapp.com 申请并替换 */
        const val TOKEN = "UYbgMHe4WC2i2OVz"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}