package com.sample.hilt.feature.iss.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.sample.hilt.analytics.Analytics
import com.sample.hilt.analytics.di.FlurryAnalytics
import com.sample.hilt.feature.iss.databinding.FragmentIssInfoBinding
import com.sample.hilt.feature.iss.domain.IssInfo
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class IssInfoFragment : Fragment() {

    private val viewModel: IssInfoViewModel by viewModels()

    private var viewBinding: FragmentIssInfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentIssInfoBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadIssInfo().observe(viewLifecycleOwner, Observer { populateIssInfo(it) })
        viewBinding?.goToMap?.setOnClickListener { viewModel.navigateToMap() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    private fun populateIssInfo(issInfo: IssInfo) {
        viewBinding?.location?.text = "${issInfo.location.latitude}, ${issInfo.location.latitude}"
        viewBinding?.crew?.text = issInfo.crewNames.joinToString(", ")
        Timber.d(issInfo.toString())
    }
}