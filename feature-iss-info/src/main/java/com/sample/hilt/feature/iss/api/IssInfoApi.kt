package com.sample.hilt.feature.iss.api

import com.sample.hilt.feature.iss.api.response.AstronautsInfo
import com.sample.hilt.feature.iss.api.response.IssPositionResponse
import retrofit2.http.GET

interface IssInfoApi {

    @GET("iss-now.json")
    suspend fun getCurrentIssLocation(): IssPositionResponse

    @GET("astros.json")
    suspend fun getCurrentAstronauts(): AstronautsInfo
}