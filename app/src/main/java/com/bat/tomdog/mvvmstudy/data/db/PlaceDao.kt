package com.bat.tomdog.mvvmstudy.data.db

import com.bat.tomdog.mvvmstudy.data.model.place.City
import com.bat.tomdog.mvvmstudy.data.model.place.County
import com.bat.tomdog.mvvmstudy.data.model.place.Province
import org.litepal.LitePal

/**
 * Created by Tomdog on 2019/12/30.
 */
class PlaceDao {

    fun getProvinceList():MutableList<Province> = LitePal.findAll(Province::class.java)

    fun getCityList(provinceId:Int):MutableList<City> = LitePal.where("provinceId= ?",provinceId.toString()).find(City::class.java)

    fun getCountyList(cityId:Int):MutableList<County> = LitePal.where("cityId = ?",cityId.toString()).find(County::class.java)

    //List<Province>? 这个类型可以为空
    fun saveProvinceList(provinceList:List<Province>?){

        //provinceList?.isEmpty() 的返回值为 Boolean?类型和Boolean类型不同
        //所以需要用如下方式
        if (provinceList != null && provinceList.isNotEmpty()){
            LitePal.saveAll(provinceList)
        }
    }


    fun saveCityList(cityList:List<City>?){
        if (cityList != null&& cityList.isNotEmpty()){
            LitePal.saveAll(cityList)
        }
    }

    fun saveCountyList(countyList:List<County>?){
        if (countyList != null && countyList.isNotEmpty()){
            LitePal.saveAll(countyList)
        }
    }
}