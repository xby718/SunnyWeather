package com.xiaomi.sunnyweather.logic

import android.util.Log
import androidx.lifecycle.liveData
import com.xiaomi.sunnyweather.logic.model.DailyResponse
import com.xiaomi.sunnyweather.logic.model.Place
import com.xiaomi.sunnyweather.logic.model.PlaceResponse
import com.xiaomi.sunnyweather.logic.model.Weather
import com.xiaomi.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

/**
 * @description:重点--将place换层places
 * @author : xby
 * @email : xuebaoyu@xiaomi.com
 * @date : 2026/2/11
 * Copyright (c) Xiaomi, Inc.
 **/
object Repository {
    private const val TAG = "PlaceSearch"

    fun searchPlaces(query: String) = liveData(Dispatchers.IO){
        Log.d(TAG, "[Repository] 开始请求地点搜索, query=$query")
        val result = try {
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query = query)
            Log.d(TAG, "[Repository] 接口返回: status=${placeResponse.status}, place数量=${placeResponse.place?.size ?: 0}")
            if (placeResponse.status == "ok") {
                val places = placeResponse.place.orEmpty()
                Log.d(TAG, "[Repository] 解析成功, 地点数=${places.size}")
                Result.success(places)
            } else {
                Log.w(TAG, "[Repository] 接口 status 非 ok: ${placeResponse.status}")
                Result.failure(RuntimeException("接口返回失败(status=${placeResponse.status})，请检查网络或到彩云开放平台确认 Token 是否有效。"))
            }
        } catch (e: Exception) {
            Log.e(TAG, "[Repository] 请求异常: ${e.message}", e)
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }

    fun refreshWeather(lng: String,lat: String) = liveData(Dispatchers.IO) {
        val result = try {
            //协程作用域
            coroutineScope {
            val deferredRealtime = async {
                SunnyWeatherNetwork.getRealtimeWeather(lng,lat)        //为什么
            }
            val defferedDaily = async {
                SunnyWeatherNetwork.getDailyWeather(lng,lat)
            }
            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = defferedDaily.await()
            if(realtimeResponse.stasus == "ok" && dailyResponse.status == "ok"){
                val weather = Weather(realtimeResponse.result.realtime,dailyResponse.result.daily)
                Result.success(weather)
            }else{
                Result.failure(
                    java.lang.RuntimeException("realtime response status is ${realtimeResponse.stasus}"
                            + "daily response status is ${dailyResponse.status}"
                    )
                )
            }
        }

        }catch (e: Exception){
            Result.failure<Weather>(e)
        }
        emit(result)
    }

//    private fun <T> fire(context: CoroutineContext,block:suspend () -> Result<T>) = liveData<Result<T>>(context) {
//        val result = try {
//            block()
//        }catch (e: Exception){
//            Result.failure<T>(e)
//        }
//        emit(result)
//    }
}