package com.airqualitymonitor.umesh.apis

import com.airqualitymonitor.umesh.ui.models.CityAQIViewHolderModel
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import kotlinx.coroutines.channels.ReceiveChannel

/**
 * Air quality service
 *
 * @constructor Create empty Air quality service
 */
interface AirQualityService {

    @Receive
    fun observeEvent(): ReceiveChannel<WebSocket.Event>

    @Receive
    fun observeCityAQI(): ReceiveChannel<List<CityAQIViewHolderModel>>

}
