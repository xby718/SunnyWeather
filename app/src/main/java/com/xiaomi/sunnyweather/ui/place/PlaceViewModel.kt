package com.xiaomi.sunnyweather.ui.place

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.xiaomi.sunnyweather.logic.Repository
import com.xiaomi.sunnyweather.logic.model.Place
/**
 * @description:ViewModel层
 * 其中，switchMap详细见13.4.2，很重要
 * @author : xby
 * @email : xuebaoyu@xiaomi.com
 * @date : 2026/2/11
 * Copyright (c) Xiaomi, Inc.
 **/
class PlaceViewModel: ViewModel() {
    companion object {
        private const val TAG = "PlaceSearch"
    }
    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()

    //新版写法
    val placeLiveData = searchLiveData.switchMap{ query ->
        Log.d(TAG, "[ViewModel] switchMap 触发, query=$query")
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query: String){
        Log.d(TAG, "[ViewModel] searchPlaces 被调用, query=$query")
        searchLiveData.value = query
    }
}