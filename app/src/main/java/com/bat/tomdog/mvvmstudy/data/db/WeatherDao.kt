package com.bat.tomdog.mvvmstudy.data.db

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.bat.tomdog.mvvmstudy.MyApplication
import com.bat.tomdog.mvvmstudy.data.model.weather.Weather
import com.google.gson.Gson

/**
 * Created by Tomdog on 2019/12/30.
 */
class WeatherDao {
    fun cacheWeatherInfo(weather:Weather?){
        if (weather ==null) return

        PreferenceManager.getDefaultSharedPreferences(MyApplication.context).edit{
            val weatherInfo = Gson().toJson(weather)
            putString("weather",weatherInfo)
        }
    }

    fun getCachedWeatherInfo():Weather?{
        val weatherInfo = PreferenceManager.getDefaultSharedPreferences(MyApplication.context).getString("weather",null)
        if (weatherInfo != null){
            return Gson().fromJson(weatherInfo,Weather::class.java)
        }
        return null
    }

    fun cacheBingPic(bingPic:String?){
        if(bingPic == null) return
        PreferenceManager.getDefaultSharedPreferences(MyApplication.context).edit {
            putString("bing_pic",bingPic)
        }
    }
    fun getCachedBingPic():String? = PreferenceManager.getDefaultSharedPreferences(MyApplication.context).getString("bing_pic",null)
    /**
     * 自定义SharedPreference的扩展函数，这个还需要多理解？？？
     */
    private fun SharedPreferences.edit(action:SharedPreferences.Editor.()->Unit){
        val editor = edit()
        action(editor)
        editor.apply()
    }
}