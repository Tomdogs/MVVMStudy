package com.coolweather.coolweatherjetpack.util

import com.bat.tomdog.mvvmstudy.MainModelFactory
import com.bat.tomdog.mvvmstudy.data.PlaceRepository
import com.bat.tomdog.mvvmstudy.data.WeatherRepository
import com.bat.tomdog.mvvmstudy.data.db.WeatherDatabase
import com.bat.tomdog.mvvmstudy.data.network.WeatherNetwork
import com.bat.tomdog.mvvmstudy.ui.area.ChooseAreaModelFactory
import com.bat.tomdog.mvvmstudy.ui.weather.WeatherModelFactory


object InjectorUtil {

    private fun getPlaceRepository() = PlaceRepository.getInstance(WeatherDatabase.getPlaceDao(), WeatherNetwork.getInstance())

    private fun getWeatherRepository() = WeatherRepository.getInstance(WeatherDatabase.getWeatherDao(), WeatherNetwork.getInstance())

    fun getChooseAreaModelFactory() = ChooseAreaModelFactory(getPlaceRepository())

    fun getWeatherModelFactory() = WeatherModelFactory(getWeatherRepository())

    fun getMainModelFactory() = MainModelFactory(getWeatherRepository())

}