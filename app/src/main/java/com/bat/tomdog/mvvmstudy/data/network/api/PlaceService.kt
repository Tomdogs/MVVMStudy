package com.bat.tomdog.mvvmstudy.data.network.api

import com.bat.tomdog.mvvmstudy.data.model.place.City
import com.bat.tomdog.mvvmstudy.data.model.place.County
import com.bat.tomdog.mvvmstudy.data.model.place.Province
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Tomdog on 2019/12/30.
 */
interface PlaceService {

    @GET("api/china")
    fun getProvinces(): Call<MutableList<Province>>

    @GET("api/china/{provinceId}")
    fun getCities(@Path("provinceId") provinceId:Int):Call<MutableList<City>>

    @GET("api/china/{provinceId}/{cityId}")
    fun getCounties(@Path("provinceId") provinceId: Int,@Path("cityId") cityId:Int):Call<MutableList<County>>
}