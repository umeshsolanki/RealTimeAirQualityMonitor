package com.airqualitymonitor.umesh

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Air quality monitor app, Using Hilt DI
 *
 * @constructor Create empty Air quality monitor app
 */
@HiltAndroidApp
class AirQualityMonitorApp : Application() //Hilt will configure everything