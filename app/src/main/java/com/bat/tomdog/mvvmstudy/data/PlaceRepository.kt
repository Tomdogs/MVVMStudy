package com.bat.tomdog.mvvmstudy.data

import android.util.Log
import com.bat.tomdog.mvvmstudy.data.db.PlaceDao
import com.bat.tomdog.mvvmstudy.data.network.WeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Tomdog on 2019/12/30.
 */
class PlaceRepository private constructor(private val placeDao: PlaceDao,private val network: WeatherNetwork){

    suspend fun getProvinceList() = withContext(Dispatchers.IO){
        var list = placeDao.getProvinceList()
        if (list.isEmpty()){
            list = network.fetchProvinceList()
            placeDao.saveProvinceList(list)
        }
        list
    }

    suspend fun getCityList(provinceId:Int) = withContext(Dispatchers.IO){
        var list = placeDao.getCityList(provinceId)
        if (list.isEmpty()){
            list = network.fetchCityList(provinceId)
            list.forEach {
                it.provinceId = provinceId
                Log.i("PlaceRepository","it.provinceId:"+it.provinceId)
            }
            placeDao.saveCityList(list)
        }

        list
    }

    suspend fun getCountyList(provinceId: Int,cityId :Int) = withContext(Dispatchers.IO){
        var list = placeDao.getCountyList(cityId)
        if (list.isEmpty()){
            list = network.fetchCountyList(provinceId,cityId)
            list.forEach {
                it.cityId = cityId
            }
            placeDao.saveCountyList(list)
        }
        list
    }

    companion object{

        private var instant :PlaceRepository?= null

        fun getInstance(placeDao: PlaceDao,network: WeatherNetwork):PlaceRepository{

            if (instant == null){
                synchronized(PlaceRepository::class.java){
                    if(instant == null){
                        instant = PlaceRepository(placeDao,network)
                    }
                }
            }

            return instant!!
        }

    }
}