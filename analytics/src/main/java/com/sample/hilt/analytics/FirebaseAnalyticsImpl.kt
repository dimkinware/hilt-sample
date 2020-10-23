package com.sample.hilt.analytics

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

internal class FirebaseAnalyticsImpl @Inject constructor(
    @ApplicationContext context: Context
) : Analytics {

    override fun logEvent(event: String) {
        Timber.d("FirebaseAnalytics track $event")
    }
}