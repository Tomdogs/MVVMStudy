package com.bat.tomdog.mvvmstudy.ui.area

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bat.tomdog.mvvmstudy.data.PlaceRepository

/**
 * Created by Tomdog on 2019/12/31.
 */
class ChooseAreaModelFactory(private val repository: PlaceRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChooseAreaViewModel(repository) as T
    }
}