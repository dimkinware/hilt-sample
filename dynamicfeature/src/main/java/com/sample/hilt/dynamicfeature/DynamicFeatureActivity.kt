package com.sample.hilt.dynamicfeature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sample.hilt.analytics.Analytics
import com.sample.hilt.analytics.di.FirebaseAnalytics
import com.sample.hilt.di.DynamicFeatureDependencies
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class DynamicFeatureActivity : AppCompatActivity() {

    @Inject
    @FirebaseAnalytics
    lateinit var analytics: Analytics

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerDynamicFeatureComponent
            .factory()
            .create(
                EntryPointAccessors.fromApplication(
                    this,
                    DynamicFeatureDependencies::class.java
                )
            )
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_feature)
    }

    override fun onStart() {
        super.onStart()
        analytics.logEvent("Dynamic Feature started")
    }
}