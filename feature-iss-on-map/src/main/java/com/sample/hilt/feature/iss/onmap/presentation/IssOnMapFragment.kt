package com.sample.hilt.feature.iss.onmap.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.sample.hilt.analytics.Analytics
import com.sample.hilt.analytics.di.FlurryAnalytics
import com.sample.hilt.feature.iss.domain.IssLocation
import com.sample.hilt.feature.iss.onmap.R
import com.sample.hilt.feature.iss.onmap.databinding.FragmentIssMapBinding
import com.sample.hilt.feature.iss.onmap.map.MapHelper
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class IssOnMapFragment : Fragment() {

    private val viewModel: IssOnMapViewModel by viewModels()

    @FlurryAnalytics
    @Inject
    lateinit var analytics: Analytics

    @Inject
    internal lateinit var mapHelper: MapHelper

    private var viewBinding: FragmentIssMapBinding? = null

    private var map: GoogleMap? = null

    private var issMarker: Marker? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentIssMapBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.subscribeOnIssLocationUpdates().observe(viewLifecycleOwner, Observer { populateIssLocation(it) })
        (childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).getMapAsync {
            analytics.logEvent("MAP_IS_READY")
            map = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    private fun populateIssLocation(issLocation: IssLocation) {
        val iss = LatLng(issLocation.latitude, issLocation.longitude)
        if (issMarker == null) {
            issMarker = map?.addMarker(
                MarkerOptions()
                    .position(iss)
                    .title("ISS")
            )
        }
        issMarker?.position = iss
        map?.moveCamera(CameraUpdateFactory.newLatLng(iss))
        Timber.d(issLocation.toString())
    }
}