package com.bat.tomdog.mvvmstudy.data.model.weather

import com.google.gson.annotations.SerializedName

/**
 * Created by Tomdog on 2019/12/30.
 */
class Weather {
    var status = ""
    lateinit var basic: Basic
    lateinit var aqi: AQI
    lateinit var now: Now
    lateinit var suggestion: Suggestion
    @SerializedName("daily_forecast")
    lateinit var forecastList: List<Forecast>
}