package com.bat.tomdog.mvvmstudy.data.model.place

import com.google.gson.annotations.SerializedName
import org.litepal.crud.LitePalSupport

/**
 * Created by Tomdog on 2019/12/30.
 */
class County(@SerializedName("name") val countyName:String,@SerializedName("weather_id") val weatherId:String):LitePalSupport() {

    val id = 0
    var cityId = 0
}