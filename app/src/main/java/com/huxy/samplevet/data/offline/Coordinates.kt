package com.huxy.samplevet.data.offline

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coordinates")
class Coordinates {

    @PrimaryKey(autoGenerate = true)
    var mId: Int = 0
    @ColumnInfo(name = "place_name")
    lateinit var mPlaceName: String
    lateinit var mLatitude: String
    lateinit var mLongitude: String

    constructor() {}

    constructor(uName: String, uLatitude: String, uLongitude: String) {
        mPlaceName = uName
        mLatitude = uLatitude
        mLongitude = uLongitude
    }

    fun getmUPlaceName(): String {
        return mPlaceName
    }

    fun getmULatitude(): String {
        return mLatitude
    }

    fun getmULongitude(): String {
        return mLongitude
    }
}
