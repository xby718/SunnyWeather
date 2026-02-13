package com.xiaomi.sunnyweather.logic.model

import android.R
import android.health.connect.datatypes.units.Temperature
import com.google.gson.annotations.SerializedName
import java.sql.Date

/**
* @description:
* @author : xby
* @email : xuebaoyu@xiaomi.com
* @date : 2026/2/13
* Copyright (c) Xiaomi, Inc.
**/
data class DailyResponse(val status: String,val result : Result){
    data class Result(val daily : Daily)
    data class Daily(val temperature: List<Temperature>,val skycon: List<Skycon>,@SerializedName("life_index") val liftIndex:List<LifeIndex>)
    data class Temperature(val date: Date,val max : Float,val min: Float,val avg: Float)
    data class Skycon(val date: Date,val value: String)
    data class LifeIndex(val ultraviolet: List<LifeDescription>,val carWashing: List<LifeDescription>,val dressing: List<LifeDescription>,
                         val comfort: List<LifeDescription>, val coldRisk: List<LifeDescription>,)
    data class LifeDescription(val date: Date,val index: Int,val desc: String)

}