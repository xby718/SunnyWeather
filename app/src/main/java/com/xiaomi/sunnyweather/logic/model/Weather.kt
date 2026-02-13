package com.xiaomi.sunnyweather.logic.model

/**
 * @description:
 * @author : xby
 * @email : xuebaoyu@xiaomi.com
 * @date : 2026/2/13
 * Copyright (c) Xiaomi, Inc.
 **/
data class Weather(val realtime: RealtimeResponse.Realtime,val daily: DailyResponse.Daily)
