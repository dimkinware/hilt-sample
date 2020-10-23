package com.sample.hilt

import androidx.fragment.app.FragmentActivity
import com.sample.hilt.analytics.Analytics
import com.sample.hilt.analytics.di.FirebaseAnalytics
import com.sample.hilt.core.Feature
import com.sample.hilt.core.FeatureNavigator
import com.sample.hilt.feature.iss.onmap.presentation.IssOnMapFragment
import com.sample.hilt.feature.iss.presentation.IssInfoFragment
import timber.log.Timber
import javax.inject.Inject

class FeatureNavigatorImpl @Inject constructor(
    private val activity: FragmentActivity,
    @FirebaseAnalytics private val analytics: Analytics
) : FeatureNavigator {

    init {
        Timber.d("FeatureNavigatorImpl created")
    }

    override fun navigateTo(feature: Feature) {
        val fragment = when (feature) {
            Feature.ISS_INFO -> IssInfoFragment()
            Feature.ISS_ON_MAP -> IssOnMapFragment()
        }

        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .addToBackStack(fragment::class.java.canonicalName)
            .commit()

        analytics.logEvent("NAVIGATE_TO_${feature.name}")
    }
}