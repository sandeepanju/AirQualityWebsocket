package com.example.airqualitywebsocket.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airqualitywebsocket.pojo.MData
import com.example.airqualitywebsocket.pojo.ViewState
import com.example.airqualitywebsocket.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel(private val repository: MainRepository) : ViewModel() {
    val dataObserve :StateFlow<ViewState<List<MData>>> get() = _dataObserve.asStateFlow()
    private val _dataObserve: MutableStateFlow<ViewState<List<MData>>> =
        MutableStateFlow(ViewState.Loading)


    fun getAirQuality() {
        viewModelScope.launch {
            try {
                val dataList = repository.getAirQuality()
                _dataObserve.value = ViewState.Success(dataList)
            } catch (e: Exception) {
                if (e is IOException) {
                    _dataObserve.value = ViewState.NetworkError
                } else {
                    _dataObserve.value = ViewState.Error(e.toString())
                }
                e.printStackTrace()
            }
        }
    }

}
