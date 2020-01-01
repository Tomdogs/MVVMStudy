package com.bat.tomdog.mvvmstudy.data

import com.bat.tomdog.mvvmstudy.data.db.WeatherDao
import com.bat.tomdog.mvvmstudy.data.model.weather.Weather
import com.bat.tomdog.mvvmstudy.data.network.WeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Tomdog on 2019/12/30.
 */
class WeatherRepository private constructor(private val weatherDao: WeatherDao,private val network: WeatherNetwork){

    companion object{
        private lateinit var instance:WeatherRepository

        fun getInstance(weatherDao: WeatherDao,network: WeatherNetwork):WeatherRepository{
            if (!::instance.isInitialized){
                synchronized(WeatherRepository::class.java){
                    if(!::instance.isInitialized){
                        instance = WeatherRepository(weatherDao,network)
                    }
                }
            }

            return instance
        }
    }

    suspend fun getWeather(weatherId:String): Weather {
        var weather = weatherDao.getCachedWeatherInfo()
        if (weather == null) weather = requestWeather(weatherId)
        return weather
    }

    suspend fun refreshWeather(weatherId: String) = requestWeather(weatherId)

    suspend fun getBingPic():String{
        var url = weatherDao.getCachedBingPic()
        if(url == null) url = requestBingPic()
        return url
    }

    suspend fun refreshBingPic():String= refreshBingPic()

    fun isWeatherCached() = weatherDao.getCachedWeatherInfo() !=null

    fun getCachedWeather() =weatherDao.getCachedWeatherInfo()!!

    private suspend fun requestWeather(weatherId: String) = withContext(Dispatchers.IO){
        val heWeather = network.fetchWeather(weatherId)
        val weather = heWeather.weather!![0]
        weatherDao.cacheWeatherInfo(weather)
        weather
    }

    private suspend fun requestBingPic() = withContext(Dispatchers.IO){
        val url = network.fetchBingPic()
        weatherDao.cacheBingPic(url)
        url
    }

}