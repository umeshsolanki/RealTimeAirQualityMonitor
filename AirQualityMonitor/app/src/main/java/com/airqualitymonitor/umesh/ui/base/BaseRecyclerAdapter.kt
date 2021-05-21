package com.airqualitymonitor.umesh.ui.base

import androidx.recyclerview.widget.RecyclerView

/**
 * Base recycler adapter
 *
 * @param T
 * @constructor Create empty Base recycler adapter
 */
abstract class BaseRecyclerAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>()
