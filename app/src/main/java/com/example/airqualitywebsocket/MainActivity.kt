package com.example.airqualitywebsocket

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.airqualitywebsocket.adapter.AiqListAdapter
import com.example.airqualitywebsocket.core.BaseActivity
import com.example.airqualitywebsocket.databinding.ActivityMainBinding
import com.example.airqualitywebsocket.pojo.MData
import com.example.airqualitywebsocket.pojo.ViewState
import com.example.airqualitywebsocket.viewModel.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val mainViewModel by viewModel<MainViewModel>()
    private var aiqDataList: ArrayList<MData> = ArrayList()
    private val aiqListAdapter by lazy { AiqListAdapter(aiqDataList) }

    override fun layoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding.rvData.adapter = aiqListAdapter
        mainViewModel.getAirQuality()
        lifecycleScope.launch {
            mainViewModel.dataObserve.apply {
                collect {
                    when (it) {
                        is ViewState.Loading -> {
                        }
                        is ViewState.Success -> {
                            updateAdapter(it.data)
                        }
                        is ViewState.Error -> {

                        }
                        is ViewState.NetworkError -> {

                        }
                    }
                }
            }
        }

    }

    private fun updateAdapter(data: List<MData>) {
        aiqDataList.addAll(data)
        Log.e("Receive size", aiqDataList.size.toString())
        aiqListAdapter.notifyDataSetChanged()
    }

}