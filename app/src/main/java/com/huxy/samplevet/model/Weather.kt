package com.huxy.samplevet.model

import com.google.gson.annotations.SerializedName
import kotlin.collections.List

class Weather {

    @SerializedName("city")
    var city: City? = null
    @SerializedName("cnt")
    var cnt: Long? = null
    @SerializedName("cod")
    var cod: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("icon")
    var icon: String? = null
    @SerializedName("id")
    var id: Long? = null
    @SerializedName("list")
    var list: List<com.huxy.samplevet.model.List>? = null
    @SerializedName("main")
    var main: String? = null
    @SerializedName("message")
    var message: Double? = null

}
