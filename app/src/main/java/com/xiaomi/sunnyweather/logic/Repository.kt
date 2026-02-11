package com.xiaomi.sunnyweather.logic

import androidx.lifecycle.liveData
import com.xiaomi.sunnyweather.logic.model.Place
import com.xiaomi.sunnyweather.logic.model.PlaceResponse
import com.xiaomi.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers



/**
 * @description:
 * @author : xby
 * @email : xuebaoyu@xiaomi.com
 * @date : 2026/2/11
 * Copyright (c) Xiaomi, Inc.
 **/
object Repository {
    fun searchPlaces(query: String) = liveData(Dispatchers.IO){
        val result = try {
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query = query)
            if(placeResponse.status =="ok"){
                val place = placeResponse.place
                Result.success(place)
            }else{
                Result.failure(RuntimeException("Response status is ${placeResponse.status}"))
            }
        }catch (e: Exception){
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}