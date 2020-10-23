package com.sample.hilt.analytics.di

import com.sample.hilt.analytics.Analytics
import com.sample.hilt.analytics.FirebaseAnalyticsImpl
import com.sample.hilt.analytics.FlurryAnalyticsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class FirebaseAnalytics

@Qualifier
annotation class FlurryAnalytics

@Module
@InstallIn(ApplicationComponent::class)
abstract class AnalyticsModule {

    @FirebaseAnalytics
    @Singleton
    @Binds
    internal abstract fun bindFirebaseAnalytics(analyticsImpl : FirebaseAnalyticsImpl) : Analytics

    @FlurryAnalytics
    @Singleton
    @Binds
    internal abstract fun bindFlurryAnalytics(analyticsImpl : FlurryAnalyticsImpl) : Analytics
}