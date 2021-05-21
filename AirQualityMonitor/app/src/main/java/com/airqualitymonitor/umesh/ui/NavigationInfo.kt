package com.airqualitymonitor.umesh.ui

import android.os.Bundle

/**
 * Screen Navigation Model, used to taken navigation decision and holds extra data to pass as bundle
 * @param action: Screen Navigation id
 * @param extra: Bundle, For passing extra data from one screen to another one
 */
data class NavigationInfo(
    val action: Int,
    var extra: Bundle? = null
)