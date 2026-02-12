package com.xiaomi.sunnyweather.logic

import android.util.Log
import androidx.lifecycle.liveData
import com.xiaomi.sunnyweather.logic.model.Place
import com.xiaomi.sunnyweather.logic.model.PlaceResponse
import com.xiaomi.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers

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
}