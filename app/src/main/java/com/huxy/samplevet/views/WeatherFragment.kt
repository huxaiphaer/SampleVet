package com.huxy.samplevet.views


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.huxy.samplevet.R
import com.huxy.samplevet.adapter.WeatherAdapter
import com.huxy.samplevet.data.remote.Utils.APP_ID
import com.huxy.samplevet.viewmodel.WeatherViewModel
import com.huxy.samplevet.views.BookmarkingFragment.Companion.LATITUDE
import com.huxy.samplevet.views.BookmarkingFragment.Companion.LONGITUDE


/**
 * A simple [Fragment] subclass.
 */
class WeatherFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var weatherViewModel: WeatherViewModel? = null
    private var pb: ProgressBar? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_weather, container, false)
        recyclerView = view.findViewById(R.id.weather_rv)
        pb = view.findViewById(R.id.pb)

        pb!!.visibility = View.VISIBLE

        recyclerView!!.layoutManager = LinearLayoutManager(activity)
        recyclerView!!.setHasFixedSize(true)

        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)


        val bundle = arguments


        if (bundle != null) {


            val latitude = bundle.getString(LATITUDE)
            val longitude = bundle.getString(LONGITUDE)

            weatherViewModel!!.getAllWeatherForecast(latitude!!, longitude!!, APP_ID).observe(this,
                Observer { weather ->
                    prepareRecyclerView(weather.list)
                    pb!!.visibility = View.GONE
                })
        }

        return view
    }

    fun prepareRecyclerView(weatherList: List<com.huxy.samplevet.model.List>?) {


        val weatherAdapter = weatherList?.let { activity?.let { it1 -> WeatherAdapter(it, it1) } }
        recyclerView!!.layoutManager = LinearLayoutManager(activity)
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.adapter = weatherAdapter
    }


}// Required empty public constructor
