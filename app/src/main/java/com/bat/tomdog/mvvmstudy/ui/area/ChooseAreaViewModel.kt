package com.bat.tomdog.mvvmstudy.ui.area

import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bat.tomdog.mvvmstudy.MyApplication
import com.bat.tomdog.mvvmstudy.data.PlaceRepository
import com.bat.tomdog.mvvmstudy.data.model.place.City
import com.bat.tomdog.mvvmstudy.data.model.place.County
import com.bat.tomdog.mvvmstudy.data.model.place.Province
import com.bat.tomdog.mvvmstudy.ui.area.ChooseAreaFragment.Companion.LEVEL_CITY
import com.bat.tomdog.mvvmstudy.ui.area.ChooseAreaFragment.Companion.LEVEL_COUNTY
import com.bat.tomdog.mvvmstudy.ui.area.ChooseAreaFragment.Companion.LEVEL_PROVINCE
import kotlinx.coroutines.launch

/**
 * Created by Tomdog on 2019/12/31.
 */
class ChooseAreaViewModel(private val repository: PlaceRepository) :ViewModel() {

    var currentLevel = MutableLiveData<Int>()

    var dataChanged = MutableLiveData<Int>()

    var isLoading = MutableLiveData<Boolean>()

    var areaSelected = MutableLiveData<Boolean>()

    var selectedProvince: Province? = null

    var selectedCity:City?=null

    var selectedCounty:County?=null

    lateinit var provinces:MutableList<Province>

    lateinit var cities:MutableList<City>

    lateinit var countries:MutableList<County>

    val dataList = ArrayList<String>()

    fun getProvinces(){
        currentLevel.value = LEVEL_PROVINCE
        launch{
            provinces = repository.getProvinceList()
            dataList.addAll(provinces.map { it.provinceName })
        }
    }

    private fun getCities() = selectedProvince?.let {
        currentLevel.value = LEVEL_CITY
        launch {
            cities = repository.getCityList(it.provinceCode)
            dataList.addAll(cities.map { it.cityName })
        }
    }

    private fun getCounties() = selectedCity?.let {
        currentLevel.value = LEVEL_COUNTY
        launch {
            countries = repository.getCountyList(it.provinceId,it.cityCode)
            dataList.addAll(countries.map { it.countyName })
        }
    }

    fun onListViewItemClick(parent:AdapterView<*>,view:View,position:Int,id:Long){
        when{
            currentLevel.value == LEVEL_PROVINCE ->{
                selectedProvince = provinces[position]
                getCities()
            }
            currentLevel.value == LEVEL_CITY ->{
                selectedCity = cities[position]
                getCounties()
            }

            currentLevel.value == LEVEL_COUNTY ->{
                selectedCounty = countries[position]
                areaSelected.value = true
            }
        }
    }

    fun onBack(){
        if(currentLevel.value == LEVEL_COUNTY){
            getCities()
        }else if (currentLevel.value == LEVEL_CITY){
            getProvinces()
        }
    }
    private fun launch(block:suspend() -> Unit) = viewModelScope.launch {
        try {
            isLoading.value = true
            dataList.clear()
            block()
            dataChanged.value = dataChanged.value?.plus(1)
            isLoading.value = false
        }catch (t:Throwable){
            t.printStackTrace()
            Toast.makeText(MyApplication.context,t.message,Toast.LENGTH_LONG).show()
            dataChanged.value = dataChanged.value?.plus(1)
            isLoading.value = false
        }
    }

}