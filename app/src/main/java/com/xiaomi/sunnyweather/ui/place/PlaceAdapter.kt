package com.xiaomi.sunnyweather.ui.place

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.xiaomi.sunnyweather.R
import com.xiaomi.sunnyweather.databinding.PlaceItemBinding
import com.xiaomi.sunnyweather.logic.model.Place

/**
 * @description:适配器
 * @author : xby
 * @email : xuebaoyu@xiaomi.com
 * @date : 2026/2/11
 * Copyright (c) Xiaomi, Inc.
 **/
class PlaceAdapter(private val fragment: Fragment,private val placeList: List<Place>):
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {

//    private lateinit var binding: PlaceItemBinding                错误！！！！！

    //ViewHolder中定义两个控件
//    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
//        val placeName : TextView = view.findViewById(R.id.placeName)
//        val placeAddress : TextView = view.findViewById(R.id.placeAddress)
//    }
    inner class ViewHolder(val binding: PlaceItemBinding) : RecyclerView.ViewHolder(binding.root)


    //创建ViewHolder
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceAdapter.ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.place_item,parent,false)
//        return ViewHolder(view)
//    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PlaceItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    //把数据绑定到 View 上
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = placeList[position]
        holder.binding.placeName.text = place.name
        holder.binding.placeAddress.text = place.address
    }

    //告诉 RecyclerView 一共有多少个条目
    override fun getItemCount(): Int {
        return placeList.size
    }
}