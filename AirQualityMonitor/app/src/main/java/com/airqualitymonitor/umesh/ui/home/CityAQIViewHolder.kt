package com.airqualitymonitor.umesh.ui.home

import com.airqualitymonitor.umesh.databinding.ViewholderCityAqiCardBinding
import com.airqualitymonitor.umesh.ui.base.BaseViewHolderModel
import com.airqualitymonitor.umesh.ui.base.BaseViewHolder
import com.airqualitymonitor.umesh.ui.base.OnClick
import com.airqualitymonitor.umesh.ui.models.CityAQIViewHolderModel

/**
 * City AQI view holder
 *
 * @property binding
 * @property onClick
 * @constructor Create empty City aqi view holder
 */
class CityAQIViewHolder(
    private val binding: ViewholderCityAqiCardBinding,
    private val onClick: OnClick<BaseViewHolderModel>?
) : BaseViewHolder<CityAQIViewHolderModel>(binding.root) {

    override fun onBindData(data: CityAQIViewHolderModel) {
        binding.model = data
        binding.listener = onClick
    }

}
