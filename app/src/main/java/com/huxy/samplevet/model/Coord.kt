package com.huxy.samplevet.model

import com.google.gson.annotations.SerializedName

class Coord {

    @SerializedName("lat")
    var lat: Double? = null
    @SerializedName("lon")
    var lon: Double? = null

}
