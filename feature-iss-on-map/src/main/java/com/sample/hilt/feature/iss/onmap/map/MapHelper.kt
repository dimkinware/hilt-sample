package com.sample.hilt.feature.iss.onmap.map

import javax.inject.Inject

internal interface MapHelper {
    fun dummy()
}

internal class IssMapHelper @Inject constructor() : MapHelper {
    override fun dummy() {
        TODO("Not yet implemented")
    }
}
