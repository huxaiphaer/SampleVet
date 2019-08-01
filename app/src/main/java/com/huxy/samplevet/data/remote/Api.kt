package com.huxy.samplevet.data.remote

import com.huxy.samplevet.model.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/data/2.5/forecast/")
    fun getWeatherForecast(
        @Query("lat") lat: String,
        @Query("lon") longitude: String,
        @Query("APPID") AppId: String
    ): Call<Weather>

}
