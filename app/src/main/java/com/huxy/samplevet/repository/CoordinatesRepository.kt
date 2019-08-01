package com.huxy.samplevet.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.huxy.samplevet.data.offline.Coordinates
import com.huxy.samplevet.data.offline.CoordinatesDatabase
import com.huxy.samplevet.data.offline.dao.CoordinatesDao

class CoordinatesRepository(application: Application) {

    private val mCoordinatesDao: CoordinatesDao
    val allCoordinates: LiveData<List<Coordinates>>

    init {
        val db = CoordinatesDatabase.getDatabase(application)
        mCoordinatesDao = db!!.coordinatesDao()
        allCoordinates = mCoordinatesDao.allPlaces
    }

    fun insert(word: Coordinates) {
        insertAsyncTask(mCoordinatesDao).execute(word)
    }

    fun deleteSingle(mId: Int) {

        deleteAsyncTask(mCoordinatesDao).execute(mId)
    }

    private class insertAsyncTask internal constructor(private val mAsyncTaskDao: CoordinatesDao) :
        AsyncTask<Coordinates, Void, Void>() {

        override fun doInBackground(vararg params: Coordinates): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }

    private class deleteAsyncTask internal constructor(private val mAsyncTaskDao: CoordinatesDao) :
        AsyncTask<Int, Void, Void>() {


        protected override fun doInBackground(vararg params: Int?): Void? {
            params[0]?.let { mAsyncTaskDao.deleteSingle(it) }
            return null
        }
    }

}
