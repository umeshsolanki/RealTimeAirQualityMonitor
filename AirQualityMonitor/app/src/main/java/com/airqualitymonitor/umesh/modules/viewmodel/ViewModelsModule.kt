package com.airqualitymonitor.umesh.modules.viewmodel

import com.airqualitymonitor.umesh.apis.AirQualityService
import com.airqualitymonitor.umesh.ui.MainViewModel
import com.tinder.scarlet.Scarlet
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * View models module configures viewmodel with their dependencies
 *
 * @constructor Create empty View models module
 */
@Module
@InstallIn(ViewModelComponent::class)
object ViewModelsModule {

    /**
     * Provide home view module, here we can use repository layer also instead of directly using
     * AirQualityService
     *
     * @param service
     * @return
     */
    @Provides
    fun provideHomeViewModule(service: AirQualityService): MainViewModel {
        return MainViewModel(service)
    }


    /**
     * Provides air quality api to deal with AQI websocket events and messages
     *
     * @param scarlet
     * @return
     */
    @Provides
    fun getAirQualityApi(scarlet: Scarlet): AirQualityService {
        return scarlet.create(AirQualityService::class.java)
    }
}