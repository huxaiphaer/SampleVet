package com.huxy.samplevet.model

import com.google.gson.annotations.SerializedName

class Main {

    @SerializedName("grnd_level")
    var grndLevel: Double? = null
    @SerializedName("humidity")
    var humidity: Long? = null
    @SerializedName("pressure")
    var pressure: Double? = null
    @SerializedName("sea_level")
    var seaLevel: Double? = null
    @SerializedName("temp")
    var temp: Double? = null
    @SerializedName("temp_kf")
    var tempKf: Double? = null
    @SerializedName("temp_max")
    var tempMax: Double? = null
    @SerializedName("temp_min")
    var tempMin: Double? = null

}
