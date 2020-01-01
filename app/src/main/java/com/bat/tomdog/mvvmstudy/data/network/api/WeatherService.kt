package com.bat.tomdog.mvvmstudy.data.network.api


import com.bat.tomdog.mvvmstudy.data.model.weather.HeWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Tomdog on 2019/12/30.
 */
interface WeatherService {

    @GET("api/weather")
    fun getWeather(@Query("cityid") weatherId:String): Call<HeWeather>

    @GET("api/bing_pic")
    fun getBingPic():Call<String>
}