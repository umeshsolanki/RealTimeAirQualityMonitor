package com.airqualitymonitor.umesh.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.airqualitymonitor.umesh.R
import com.airqualitymonitor.umesh.databinding.ViewholderCityAqiCardBinding
import com.airqualitymonitor.umesh.ui.base.BaseRecyclerAdapter
import com.airqualitymonitor.umesh.ui.base.BaseViewHolderModel
import com.airqualitymonitor.umesh.ui.base.BaseViewHolder
import com.airqualitymonitor.umesh.ui.base.OnClick
import com.airqualitymonitor.umesh.ui.home.CityAQIViewHolder
import com.airqualitymonitor.umesh.ui.models.CityAQIViewHolderModel

/**
 * Recyclerview Adapter responsible for data population in Recyclerview
 * @param itemList: list of items to display
 * @param onClick: Click listener
 */
class HomeDataAdapter(
    private val onClick: OnClick<BaseViewHolderModel>?,
    private val itemList: List<BaseViewHolderModel>
) : BaseRecyclerAdapter<BaseViewHolderModel>() {

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<BaseViewHolderModel> {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.viewholder_city_aqi_card -> {
                CityAQIViewHolder(
                    DataBindingUtil.inflate(inflater, viewType, parent, false),
                    onClick
                ) as BaseViewHolder<BaseViewHolderModel>
            }
            else -> throw IllegalArgumentException("View holder not registered in adapter")
        }
    }

    override fun getItemId(position: Int): Long {
        when (val model = itemList[position]) {
            is CityAQIViewHolderModel -> {
                return model.city.hashCode().toLong()
            }
        }
        return super.getItemId(position)
    }

    override fun getItemCount() = itemList.size

    override fun getItemViewType(position: Int) = itemList[position].layoutRes

    override fun onBindViewHolder(holder: BaseViewHolder<BaseViewHolderModel>, position: Int) {
        holder.onBindData(itemList[position])
    }

}
