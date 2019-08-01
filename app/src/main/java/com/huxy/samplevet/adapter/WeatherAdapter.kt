package com.huxy.samplevet.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.huxy.samplevet.R

class WeatherAdapter(private val mWeatherList: List<com.huxy.samplevet.model.List>, private val mContext: Context) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {

        val layoutView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weather_forecast, parent, false)
        return WeatherViewHolder(layoutView)

    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {

        val weather = mWeatherList[position]
        holder.weather_name.text = weather.weather!![0].main
        holder.temperature_txt.text = weather.main!!.temp.toString()
        holder.wind_speed_txt.text = weather.wind!!.speed.toString()
        holder.date_txt.text = weather.dtTxt

    }

    override fun getItemCount(): Int {
        return mWeatherList.size
    }

    inner class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val weather_name: TextView
        val temperature_txt: TextView
        val wind_speed_txt: TextView
        val date_txt: TextView


        init {
            weather_name = itemView.findViewById(R.id.weather_name)
            temperature_txt = itemView.findViewById(R.id.temperature_txt)
            wind_speed_txt = itemView.findViewById(R.id.wind_speed_txt)
            date_txt = itemView.findViewById(R.id.date_txt)


        }
    }
}
