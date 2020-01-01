package com.bat.tomdog.mvvmstudy.data.model.weather

import com.google.gson.annotations.SerializedName

/**
 * Created by Tomdog on 2019/12/30.
 */
class Basic {
    @SerializedName("city")
    var cityName = ""
    @SerializedName("id")
    var weatherId = ""
    lateinit var update: Update

    inner class Update {
        @SerializedName("loc")
        var updateTime = ""

        fun time() = updateTime.split(" ")[1]
    }
}