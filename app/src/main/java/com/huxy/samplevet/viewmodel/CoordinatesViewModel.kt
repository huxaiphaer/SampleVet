package com.huxy.samplevet.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.huxy.samplevet.data.offline.Coordinates
import com.huxy.samplevet.repository.CoordinatesRepository

class CoordinatesViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository: CoordinatesRepository

    val allCoordinates: LiveData<List<Coordinates>>

    init {

        mRepository = CoordinatesRepository(application)
        allCoordinates = mRepository.allCoordinates
    }

    fun insert(coordinates: Coordinates) {
        mRepository.insert(coordinates)
    }
}
