package com.sample.hilt.core

enum class Feature {
    ISS_INFO,
    ISS_ON_MAP
}

interface FeatureNavigator {
    fun navigateTo(feature: Feature)
}