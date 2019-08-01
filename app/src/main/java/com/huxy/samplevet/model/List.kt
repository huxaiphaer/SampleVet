package com.huxy.samplevet.model

import com.google.gson.annotations.SerializedName
import kotlin.collections.List

class List {

    @SerializedName("clouds")
    var clouds: Clouds? = null
    @SerializedName("dt")
    var dt: Long? = null
    @SerializedName("dt_txt")
    var dtTxt: String? = null
    @SerializedName("main")
    var main: Main? = null
    @SerializedName("sys")
    var sys: Sys? = null
    @SerializedName("weather")
    var weather: List<Weather>? = null
    @SerializedName("wind")
    var wind: Wind? = null

}
