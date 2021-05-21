package com.airqualitymonitor.umesh.ui

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airqualitymonitor.umesh.apis.AirQualityService
import com.airqualitymonitor.umesh.constants.ArgConstants
import com.airqualitymonitor.umesh.ui.base.BaseViewHolderModel
import com.airqualitymonitor.umesh.ui.base.OnClick
import com.airqualitymonitor.umesh.ui.models.ChartViewHolderModel
import com.airqualitymonitor.umesh.ui.models.CityAQIViewHolderModel
import com.tinder.scarlet.WebSocket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Main view model is used to keep data related to ui, It's shared viewmodel. Same viewmodel is
 * used by both fragments
 *
 * @property service
 * @constructor Create empty Main view model
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    //instead of injecting service directly into VM, we can use Repository here to fetch and map data
    private val service: AirQualityService
) : ViewModel(), OnClick<BaseViewHolderModel> {

    private val TAG = MainViewModel::class.java.simpleName

    //It'll hold individual city's latest data, key as city name and values will be latest AQI
    private val latestRecordMap = mutableMapOf<String, CityAQIViewHolderModel>()

    //Used this list to keep single reference to the list, will help Recycler view to keep track of updated/old entries
    private val uiListReference = mutableListOf<BaseViewHolderModel>()

    //used this to notify Recyclerview/chart
    private val _liveUiData = MutableLiveData<List<BaseViewHolderModel>>(uiListReference)

    val liveUiData: LiveData<List<BaseViewHolderModel>>
        get() = _liveUiData

    //selectedCity to monitor
    var selectedCity: CityAQIViewHolderModel? = null
        set(value) {
            uiListReference.clear()
            field = value
        }

    //Used to handle USer events and navigation
    private val _navigationLiveData = MutableLiveData<NavigationInfo>()
    val navigationLiveData: LiveData<NavigationInfo>
        get() = _navigationLiveData


    /**
     * Fetch home data
     *
     */
    fun fetchHomeData() {
        Log.d(TAG, "Fetching home data")
        viewModelScope.launch {
            service.observeEvent().receiveAsFlow().filter {
                it is WebSocket.Event.OnConnectionOpened<*>
            }.collect {
                service.observeCityAQI().receiveAsFlow().map { extractInfo(it) }.collect { list ->
                    uiListReference.apply {
                        if (selectedCity == null) {
                            clear()
                            addAll(latestRecordMap.values.sortedBy { it.aqi })
                            _liveUiData.value = this
                        } else {
                            val now = System.currentTimeMillis()
                            removeIf { it is ChartViewHolderModel && (now - it.lastUpdated) > 30000 }
                            addAll(list.filter { it.city == selectedCity?.city })
                            _liveUiData.value = this
                        }
                    }
                }
            }
        }
    }

    /**
     * Extract info to populate home ui
     *
     * @param list from API
     * @return
     */
    private fun extractInfo(list: List<CityAQIViewHolderModel>): List<CityAQIViewHolderModel> {
        //Null meaning user is in chart fragment
        if (selectedCity == null) {
            list.forEach {
                latestRecordMap[it.city] = it
            }
        }
        return list
    }

    /**
     * viewholder's clicks are delegated here
     *
     * @param viewId
     * @param dataModel
     */
    override fun onClick(viewId: Int, dataModel: Any?) {
        if (dataModel is CityAQIViewHolderModel) {
            _navigationLiveData.value = NavigationInfo(viewId, Bundle().apply {
                putParcelable(ArgConstants.NAV_DATA, dataModel)
            })
            selectedCity = dataModel
        }
    }

    /**
     * Tear down viewmodel references
     *
     */
    override fun onCleared() {
        selectedCity = null
        super.onCleared()
    }
}