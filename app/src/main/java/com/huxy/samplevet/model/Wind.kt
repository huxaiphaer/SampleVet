package com.huxy.samplevet.model

import com.google.gson.annotations.SerializedName

class Wind {

    @SerializedName("deg")
    var deg: Double? = null
    @SerializedName("speed")
    var speed: Double? = null

}
