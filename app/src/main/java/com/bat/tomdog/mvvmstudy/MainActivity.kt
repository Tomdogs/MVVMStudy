package com.bat.tomdog.mvvmstudy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.bat.tomdog.mvvmstudy.ui.area.ChooseAreaFragment
import com.bat.tomdog.mvvmstudy.ui.weather.WeatherActivity
import com.coolweather.coolweatherjetpack.util.InjectorUtil

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this,InjectorUtil.getMainModelFactory()).get(MainViewModel::class.java)

        if(viewModel.isWeatherCached()){
            val intent = Intent(this,WeatherActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            supportFragmentManager.beginTransaction().replace(R.id.container,ChooseAreaFragment()).commit()
        }
    }
}
