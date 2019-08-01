package com.huxy.samplevet.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.huxy.samplevet.model.Weather
import com.huxy.samplevet.repository.WeatherRepository

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    private val weatherRepository: WeatherRepository

    init {
        weatherRepository = WeatherRepository(application)
    }


    fun getAllWeatherForecast(lat: String, longi: String, App_Id: String): LiveData<Weather> {

        return weatherRepository.getMutableLiveData(lat, longi, App_Id)
    }


}
