package com.xiaomi.sunnyweather.ui.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import kotlin.getValue
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.xiaomi.sunnyweather.R
import com.xiaomi.sunnyweather.databinding.FragmentPlaceBinding


/**
 * @description:
 * @author : xby
 * @email : xuebaoyu@xiaomi.com
 * @date : 2026/2/12
 * Copyright (c) Xiaomi, Inc.
 **/
class PlaceFragment: Fragment() {

    private var _binding : FragmentPlaceBinding ?= null
    private val binding get() = _binding!!

    private val viewModel: PlaceViewModel by viewModels()               //获取viewModels实例

    private lateinit var adapter: PlaceAdapter

    //在onCreateView方法中加载布局，使用viewbinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //老版本
//        return inflater.inflate(R.layout.fragment_place,container,false)

        //binding写法
        _binding = FragmentPlaceBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 1️⃣ 设置布局管理器
        binding.recycleView.layoutManager = LinearLayoutManager(requireContext())
        // 2️⃣ 创建适配器
//        adapter = PlaceAdapter(this, listOf())
        adapter = PlaceAdapter(this, viewModel.placeList)
        // 3️⃣ 设置适配器
        binding.recycleView.adapter = adapter

        binding.searchPlaceEdit.doOnTextChanged { text, _, _, _ ->
            val content = text?.toString() ?: ""
            if(content.isNotEmpty()){
                viewModel.searchPlaces(content)
            }else{
                binding.recycleView.visibility = View.GONE
                binding.bgImageView.visibility = View.VISIBLE
                viewModel.placeList.clear()
                adapter.notifyDataSetChanged()
            }
        }

        viewModel.placeLiveData.observe(viewLifecycleOwner){ result ->
            val place = result.getOrNull()
            if(place != null){
                binding.recycleView.visibility = View.VISIBLE
                binding.bgImageView.visibility = View.GONE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(place)
                adapter.notifyDataSetChanged()
            }else{
                Toast.makeText(activity,"未能查询到任何地点", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        }
    }

    //必须要有
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}