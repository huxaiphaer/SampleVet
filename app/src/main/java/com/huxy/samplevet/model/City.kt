package com.huxy.samplevet.model

import com.google.gson.annotations.SerializedName

class City {

    @SerializedName("coord")
    var coord: Coord? = null
    @SerializedName("country")
    var country: String? = null
    @SerializedName("id")
    var id: Long? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("timezone")
    var timezone: Long? = null

}
