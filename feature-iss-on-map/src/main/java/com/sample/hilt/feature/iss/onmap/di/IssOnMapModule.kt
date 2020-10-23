package com.sample.hilt.feature.iss.onmap.di

import com.sample.hilt.feature.iss.onmap.map.IssMapHelper
import com.sample.hilt.feature.iss.onmap.map.MapHelper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
internal abstract class IssOnMapModule {

    @Binds
    internal abstract fun bindMapHelper(impl: IssMapHelper) : MapHelper
}