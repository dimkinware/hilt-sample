package com.sample.hilt.feature.iss.onmap.presentation

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.hilt.feature.iss.domain.IssLocation
import com.sample.hilt.feature.iss.repository.IssInfoRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class IssOnMapViewModel  @ViewModelInject constructor(
    private val issInfoRepository: IssInfoRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val issLocations = MutableLiveData<IssLocation>()

    fun subscribeOnIssLocationUpdates() : LiveData<IssLocation> {
        viewModelScope.launch {
            issInfoRepository.subscribeOnIssLocationUpdates().collect {
                issLocations.value = it
            }
        }
        return issLocations
    }
}