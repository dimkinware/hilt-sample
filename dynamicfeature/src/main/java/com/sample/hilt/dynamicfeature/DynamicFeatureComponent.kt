package com.sample.hilt.dynamicfeature

import com.sample.hilt.di.DynamicFeatureDependencies
import dagger.Component

@Component(dependencies = [DynamicFeatureDependencies::class])
interface DynamicFeatureComponent {

    fun inject(activity: DynamicFeatureActivity)

    @Component.Factory
    interface Factory {
        fun create(
            appDependencies: DynamicFeatureDependencies
        ) : DynamicFeatureComponent
    }
}