package com.huxy.samplevet.views


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.huxy.samplevet.R
import com.huxy.samplevet.adapter.CoordinatesAdapter
import com.huxy.samplevet.adapter.OnItemClickListener
import com.huxy.samplevet.data.offline.Coordinates
import com.huxy.samplevet.viewmodel.CoordinatesViewModel


/**
 * A simple [Fragment] subclass.
 */
class BookmarkingFragment : Fragment() {
    internal lateinit var adapter: CoordinatesAdapter
    private var coordinatesViewModel: CoordinatesViewModel? = null
    private var recyclerView: RecyclerView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_bookmarking, container, false)

        recyclerView = view.findViewById(R.id.rv)


        Toast.makeText(context, "Tap on any city to view the weather forecast.", Toast.LENGTH_LONG).show()

        coordinatesViewModel = ViewModelProviders.of(this).get(CoordinatesViewModel::class.java)
        coordinatesViewModel!!.allCoordinates.observe(this, Observer { coordinates ->
            adapter = CoordinatesAdapter(coordinates, activity!!, object : OnItemClickListener {
                override fun onItemClicked(position: Coordinates) {

                    val bundle = Bundle()
                    bundle.putString(LATITUDE, position.mLatitude)
                    bundle.putString(LONGITUDE, position.mLongitude)

                    val navController = Navigation.findNavController(activity!!, R.id.navHostFragment)
                    navController.navigate(R.id.action_bookmarking_to_weatherFragment, bundle)


                }
            }, activity!!.application)
            recyclerView!!.layoutManager = LinearLayoutManager(activity)
            recyclerView!!.setHasFixedSize(true)
            recyclerView!!.adapter = adapter
        })

        return view
    }

    companion object {

        var LATITUDE = "latitude"
        var LONGITUDE = "longitude"
    }


}
