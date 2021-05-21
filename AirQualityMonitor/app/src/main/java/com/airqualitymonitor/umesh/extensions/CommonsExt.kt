package com.airqualitymonitor.umesh.extensions

import android.text.format.DateFormat
import java.util.*


fun Long.asHumanReadableDate(): String {
    return DateFormat.format("hh:mm:ss aa", Date(this)).toString()
}