package com.bat.tomdog.mvvmstudy.data.db

/**
 * Created by Tomdog on 2019/12/30.
 */
object WeatherDatabase {

    private var placeDao:PlaceDao? = null

    private var weatherDao:WeatherDao? = null

    fun getPlaceDao():PlaceDao{
        if (placeDao == null){
            placeDao = PlaceDao()
        }

        return placeDao!!
    }

    fun getWeatherDao():WeatherDao{
        if(weatherDao == null){
            weatherDao = WeatherDao()
        }
        return weatherDao!!
    }
}