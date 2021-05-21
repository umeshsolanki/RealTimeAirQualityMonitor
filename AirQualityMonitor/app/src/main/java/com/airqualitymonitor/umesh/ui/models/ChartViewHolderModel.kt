package com.airqualitymonitor.umesh.ui.models

import android.os.Parcelable
import com.airqualitymonitor.umesh.ui.base.BaseViewHolderModel
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

/**
 * Chart view holder data model, holds viewtype and data related to this view
 *
 * @property aqi
 * @property lastUpdated
 * @property layoutRes
 * @constructor Create empty Chart view holder model
 */
@Parcelize
@JsonClass(generateAdapter = true)
open class ChartViewHolderModel(
    open var aqi: Float = 0f,
    val lastUpdated: Long = System.currentTimeMillis(),
    override var layoutRes: Int = -1
) : BaseViewHolderModel, Parcelable
