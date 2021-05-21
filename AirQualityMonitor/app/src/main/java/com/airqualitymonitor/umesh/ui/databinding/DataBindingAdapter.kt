package com.airqualitymonitor.umesh.ui.databinding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.airqualitymonitor.umesh.ui.HomeDataAdapter
import com.airqualitymonitor.umesh.ui.base.BaseViewHolderModel
import com.airqualitymonitor.umesh.ui.base.OnClick

/**
 * Data binding adapter used to create data binding adapters
 *
 * @constructor Create empty Data binding adapter
 */
object DataBindingAdapter {

    val TAG = DataBindingAdapter::class.java.simpleName

    @BindingAdapter("items", "onClick")
    @JvmStatic
    fun setRecyclerViewPropertiesForHomeScreen(
        recyclerView: RecyclerView,
        data: LiveData<out List<BaseViewHolderModel>>?,
        onClick: OnClick<BaseViewHolderModel>?,
    ) {
        recyclerView.apply {
            adapter?.notifyDataSetChanged() ?: kotlin.run {
                adapter = data?.value?.let {
                    HomeDataAdapter(onClick, it).apply {
                        setHasStableIds(true)
                    }
                }
                setHasFixedSize(true)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("app:bindColor")
    fun bindBackgroundColor(view: View, color: Int) {
        view.setBackgroundResource(color)
    }


}