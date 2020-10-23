package com.sample.hilt.feature.iss.presentation

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.hilt.core.Feature
import com.sample.hilt.core.FeatureNavigator
import com.sample.hilt.feature.iss.api.IssInfoApi
import com.sample.hilt.feature.iss.domain.IssInfo
import com.sample.hilt.feature.iss.repository.IssInfoRepository
import kotlinx.coroutines.launch

internal class IssInfoViewModel @ViewModelInject constructor(
    private val issInfoRepository: IssInfoRepository,
    private val featureNavigator: FeatureNavigator,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val issInfo = MutableLiveData<IssInfo>()

    fun loadIssInfo() : LiveData<IssInfo> {
        viewModelScope.launch {
            val info = issInfoRepository.getIssInfo()
            issInfo.value = info
        }
        return issInfo
    }

    fun navigateToMap() {
        featureNavigator.navigateTo(Feature.ISS_ON_MAP)
    }
}