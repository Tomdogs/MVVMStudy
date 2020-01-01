package com.bat.tomdog.mvvmstudy.ui.weather

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bat.tomdog.mvvmstudy.MyApplication
import com.bat.tomdog.mvvmstudy.data.WeatherRepository
import com.bat.tomdog.mvvmstudy.data.model.weather.Weather
import kotlinx.coroutines.launch

/**
 * Created by Tomdog on 2019/12/31.
 */
class WeatherViewModel(private val repository: WeatherRepository):ViewModel() {

    var weather = MutableLiveData<Weather>()

    var bingPicUrl = MutableLiveData<String>()

    var refreshing = MutableLiveData<Boolean>()

    var weatherInitialized = MutableLiveData<Boolean>()

    var weatherId = ""

    fun getWeather(){
        launch({
            weather.value = repository.getWeather(weatherId)

            weatherInitialized.value=true
        },{
            it.printStackTrace();
            Toast.makeText(MyApplication.context,it.message+"dsds",Toast.LENGTH_LONG).show()
        }
        )
        getBingPic(false)
    }

    fun refreshWeather(){
        refreshing.value = true
        launch({
            weather.value = repository.refreshWeather(weatherId)
            refreshing.value = false
            weatherInitialized.value = true
        },{
            it.printStackTrace();
            Toast.makeText(MyApplication.context,it.message+"dsds2",Toast.LENGTH_LONG).show()
            refreshing.value = false
        }
        )
        getBingPic(true)
    }

    fun isWeatherCache() = repository.isWeatherCached()

    fun getCachedWeather() = repository.getCachedWeather()

    fun onRefresh(){
        refreshWeather()
    }

    private fun getBingPic(refresh: Boolean) {
        launch({
            bingPicUrl.value = if(refresh) repository.refreshBingPic() else repository.getBingPic()
        },{
            it.printStackTrace();
            Toast.makeText(MyApplication.context,it.message+"dsds3",Toast.LENGTH_LONG).show()
        }
        )
    }

    private fun launch(block:suspend () -> Unit,error:suspend (Throwable) ->Unit) = viewModelScope.launch {
        try {
            block()
        }catch (e:Throwable){
            error(e)
        }
    }
}