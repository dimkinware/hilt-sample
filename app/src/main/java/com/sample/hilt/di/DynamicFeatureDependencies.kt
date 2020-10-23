package com.sample.hilt.di

import com.sample.hilt.analytics.Analytics
import com.sample.hilt.analytics.di.FirebaseAnalytics
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface DynamicFeatureDependencies {
    @FirebaseAnalytics
    fun getAnalytics() : Analytics
}