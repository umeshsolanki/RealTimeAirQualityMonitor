package com.airqualitymonitor.umesh.ui.models

import android.os.Parcelable
import androidx.annotation.ColorRes
import com.airqualitymonitor.umesh.R
import com.airqualitymonitor.umesh.extensions.asHumanReadableDate
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

/**
 * empty City aqi view holder model holds viewtype and data related to this view
 *
 * @property city
 * @property aqi
 * @property layoutRes
 * @constructor Create empty City aqi view holder model
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class CityAQIViewHolderModel(
    var city: String = "",
    override var aqi: Float = 0f,
    override var layoutRes: Int = R.layout.viewholder_city_aqi_card
) : ChartViewHolderModel(aqi), Parcelable {

    fun getFormattedAQI(): String {
        return "%.2f".format(aqi)
    }

    fun getLastUpdatedDate(): String {
        return "Updated at " + lastUpdated.asHumanReadableDate()
    }

    @IgnoredOnParcel
    @ColorRes
    var color: Int = when {
        aqi > 0 && aqi < 50 -> R.color.color_good
        aqi > 50 && aqi < 100 -> R.color.color_satisfy
        aqi > 100 && aqi < 200 -> R.color.color_moderate
        aqi > 200 && aqi < 300 -> R.color.color_poor
        aqi > 300 && aqi < 400 -> R.color.color_very_poor
        aqi > 400 && aqi < 500 -> R.color.color_sever
        else ->
            R.color.white
    }
}
