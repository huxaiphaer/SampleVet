package com.huxy.samplevet.views


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.gms.common.api.Status
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.huxy.samplevet.R
import com.huxy.samplevet.data.offline.Coordinates
import com.huxy.samplevet.data.remote.Utils
import com.huxy.samplevet.repository.CoordinatesRepository

import java.util.Arrays

import com.android.volley.VolleyLog.TAG
import com.huxy.samplevet.data.remote.Utils.PLACES_API


/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment(), OnMapReadyCallback {

    private var mMap: GoogleMap? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_main, container, false)

        autoSearch(view)

        return view
    }


    private fun autoSearch(view: View) {

        // Initialize the AutocompleteSupportFragment.

        Places.initialize(context!!, Utils.PLACES_API)

        val placesClient = Places.createClient(activity!!)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?

        mapFragment!!.getMapAsync(this)


        val autocompleteFragment =
            childFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment?


        autocompleteFragment!!.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG))


        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                Toast.makeText(context, "Tap on the Marker to bookmark the City.", Toast.LENGTH_LONG).show()

                mMap!!.addMarker(MarkerOptions().position(place.latLng!!).title(place.name!!.toString()))
                mMap!!.moveCamera(CameraUpdateFactory.newLatLng(place.latLng))
                mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(place.latLng, 12.0f))
                mMap!!.setOnMarkerClickListener {
                    AlertDialog.Builder(context)
                        .setTitle("Bookmark City")
                        .setMessage("Are you sure you want to bookmark this city")

                        .setPositiveButton(android.R.string.yes) { dialog, which ->
                            val navController = Navigation.findNavController(activity!!, R.id.navHostFragment)
                            navController.navigate(R.id.action_mainFragment2_to_bookmarking)

                            val coordinatesRepository = CoordinatesRepository(activity!!.application)
                            coordinatesRepository.insert(
                                Coordinates(
                                    place.name!!,
                                    place.latLng!!.latitude.toString(), place.latLng!!.longitude.toString()
                                )
                            )
                        }

                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show()

                    true
                }
            }

            override fun onError(status: Status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: $status")
            }
        })
    }


    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap

    }

}// Required empty public constructor
