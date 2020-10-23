package com.sample.hilt.di

import com.sample.hilt.FeatureNavigatorImpl
import com.sample.hilt.core.FeatureNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
abstract class NavigatorModule {

    @ActivityScoped
    @Binds
    abstract fun bindNavigator(navigator: FeatureNavigatorImpl) : FeatureNavigator
}