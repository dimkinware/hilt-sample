package com.sample.hilt

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sample.hilt.core.Feature
import com.sample.hilt.core.FeatureNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: FeatureNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigator.navigateTo(Feature.ISS_INFO)
        // startDynamicFeature()
    }

    private fun startDynamicFeature() {
        val intent = Intent()
        intent.setClassName(BuildConfig.APPLICATION_ID, "com.sample.hilt.dynamicfeature.DynamicFeatureActivity")
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        }
    }

}