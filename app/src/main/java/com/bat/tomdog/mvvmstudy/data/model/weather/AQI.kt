package com.bat.tomdog.mvvmstudy.data.model.weather

/**
 * Created by Tomdog on 2019/12/30.
 */
class AQI {
    lateinit var city:AQICity

    inner class AQICity{
        var aqi = ""
        var pm25 = ""
    }
}