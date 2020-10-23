package com.sample.hilt.feature.iss.api.response

import com.google.gson.annotations.SerializedName

data class AstronautsInfo(
    @SerializedName("message") override val message: String,
    @SerializedName("people") val astronauts: List<Astronaut>,
    @SerializedName("number") val number: Int
) : BaseResponse

data class Astronaut(
    @SerializedName("craft") val craft: String,
    @SerializedName("name") val name: String
)