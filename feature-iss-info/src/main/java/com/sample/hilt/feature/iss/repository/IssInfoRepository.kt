package com.sample.hilt.feature.iss.repository

import com.sample.hilt.feature.iss.api.IssInfoApi
import com.sample.hilt.feature.iss.domain.IssInfo
import com.sample.hilt.feature.iss.domain.IssLocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

interface IssInfoRepository {
    suspend fun getIssInfo() : IssInfo
    suspend fun subscribeOnIssLocationUpdates() : Flow<IssLocation>
}

internal class IssInfoRepositoryImpl @Inject constructor(
    private val api: IssInfoApi
) : IssInfoRepository {

    override suspend fun getIssInfo(): IssInfo = withContext(Dispatchers.IO) {
        val locationResponse = api.getCurrentIssLocation()
        val astronautsResponse = api.getCurrentAstronauts()
        val issLocation = IssLocation(locationResponse.position.latitude, locationResponse.position.longitude)
        IssInfo(astronautsResponse.astronauts.map { it.name }, issLocation)
    }

    override suspend fun subscribeOnIssLocationUpdates(): Flow<IssLocation> = flow {
        while (true) {
            Timber.d("send iss location response")
            val locationResponse = api.getCurrentIssLocation()
            emit(
                IssLocation(
                    locationResponse.position.latitude,
                    locationResponse.position.longitude
                )
            )
            delay(1000)
        }
    }.flowOn(Dispatchers.IO)
}