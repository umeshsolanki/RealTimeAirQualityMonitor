package com.airqualitymonitor.umesh.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * BaseViewHolder
 * @param view: View instance
 */
abstract class BaseViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun onBindData(data: T)

}
