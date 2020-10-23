package com.sample.hilt.feature.iss.api.response

import com.google.gson.annotations.SerializedName

data class IssPositionResponse(
    @SerializedName("message") override val message: String,
    @SerializedName("iss_position") val position: Position,
    @SerializedName("timestamp") val timestamp: Long
) : BaseResponse

data class Position(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double
)