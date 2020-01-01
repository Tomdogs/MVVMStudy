package com.bat.tomdog.mvvmstudy

import androidx.lifecycle.ViewModel
import com.bat.tomdog.mvvmstudy.data.WeatherRepository

/**
 * Created by Tomdog on 2019/12/31.
 */
class MainViewModel(private val repository: WeatherRepository):ViewModel() {
    fun isWeatherCached() = repository.isWeatherCached()
}