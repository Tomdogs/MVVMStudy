package com.bat.tomdog.mvvmstudy.data.model.weather

import com.google.gson.annotations.SerializedName

/**
 * Created by Tomdog on 2019/12/30.
 */
class HeWeather {

    @SerializedName("HeWeather")
    var weather:List<Weather> ?= null
}