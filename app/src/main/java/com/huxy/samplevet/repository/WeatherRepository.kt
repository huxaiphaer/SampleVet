package com.huxy.samplevet.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.huxy.samplevet.data.remote.RetrofitClient
import com.huxy.samplevet.model.Weather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepository(private val mApplication: Application) {


    private val mutableLiveData = MutableLiveData<Weather>()


    fun getMutableLiveData(lat: String, longitude: String, app_id: String): MutableLiveData<Weather> {

        RetrofitClient.instance
            .api
            .getWeatherForecast(lat, longitude, app_id)
            .enqueue(object : Callback<Weather> {
                override fun onResponse(call: Call<Weather>, response: Response<Weather>) {

                    try {
                        mutableLiveData.setValue(response.body())
                    } catch (e: Exception) {

                    }

                }

                override fun onFailure(call: Call<Weather>, t: Throwable) {

                }
            })


        return mutableLiveData
    }
}
