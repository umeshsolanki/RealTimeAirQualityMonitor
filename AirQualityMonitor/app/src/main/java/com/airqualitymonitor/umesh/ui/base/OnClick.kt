package com.airqualitymonitor.umesh.ui.base

import android.view.View

/**
 * On Click listener
 */
interface OnClick<T> {

    /**
     * On click with few params
     *
     * @param viewId to take decision
     * @param dataModel : Action Model passed in event
     */
    fun onClick(viewId: Int, dataModel: Any?)
}
