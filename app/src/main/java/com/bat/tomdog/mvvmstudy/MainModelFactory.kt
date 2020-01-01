package com.bat.tomdog.mvvmstudy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bat.tomdog.mvvmstudy.data.WeatherRepository

/**
 * Created by Tomdog on 2019/12/31.
 */
class MainModelFactory(private val repository: WeatherRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}
