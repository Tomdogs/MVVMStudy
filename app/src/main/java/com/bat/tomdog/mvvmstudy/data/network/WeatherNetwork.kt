package com.bat.tomdog.mvvmstudy.data.network

import com.bat.tomdog.mvvmstudy.data.network.api.PlaceService
import com.bat.tomdog.mvvmstudy.data.network.api.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Created by Tomdog on 2019/12/30.
 */
class WeatherNetwork {

    //伴生对象（相当于Java中s静态成员）
    companion object{
        private var network:WeatherNetwork?= null

        fun getInstance():WeatherNetwork{
            if(network == null){
                synchronized(WeatherNetwork::class.java){
                    if(network == null){
                        network = WeatherNetwork()
                    }
                }
            }
            return network!!
        }
    }

    private val placeService  = ServiceCreator.create(PlaceService::class.java)

    private val weatherService = ServiceCreator.create(WeatherService::class.java)

    suspend fun fetchProvinceList() = placeService.getProvinces().await()

    suspend fun fetchCityList(provinceId:Int) = placeService.getCities(provinceId).await()

    suspend fun fetchCountyList(provinceId: Int,cityId:Int) = placeService.getCounties(provinceId,cityId).await()


    suspend fun fetchWeather(weatherId:String) = weatherService.getWeather(weatherId).await()

    suspend fun fetchBingPic() = weatherService.getBingPic().await()


    /**
     * suspendCoroutine 获取暂停函数中的当前继续实例，并暂停当前正在运行的协程
     */
    private suspend fun <T> Call<T>.await():T{
        return suspendCoroutine {
            continuation -> enqueue(object :Callback<T>{
            override fun onFailure(call: Call<T>, t: Throwable) {
                continuation.resumeWithException(t)
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                if (body!= null) continuation.resume(body)
                else continuation.resumeWithException(RuntimeException("返回数据为空!"))
            }
        })
        }
    }


}