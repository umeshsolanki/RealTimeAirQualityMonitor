package com.airqualitymonitor.umesh.ui.graph

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.airqualitymonitor.umesh.R
import com.airqualitymonitor.umesh.constants.ArgConstants
import com.airqualitymonitor.umesh.databinding.FragmentChartBinding
import com.airqualitymonitor.umesh.ui.MainActivity
import com.airqualitymonitor.umesh.ui.MainViewModel
import com.airqualitymonitor.umesh.ui.models.ChartViewHolderModel
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dagger.hilt.android.AndroidEntryPoint

/**
 * Chart fragment
 *
 * @constructor Create empty Chart fragment
 */
@AndroidEntryPoint
class ChartFragment : Fragment(R.layout.fragment_chart) {

    private var binding: FragmentChartBinding? = null
    private var mainViewModel: MainViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChartBinding.bind(view)
        if (mainViewModel == null) {
            mainViewModel = (activity as? MainActivity)?.mainViewModel
        }
        mainViewModel?.selectedCity = arguments?.getParcelable(ArgConstants.NAV_DATA)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = mainViewModel
            cityAqiChart.description = Description().apply {
                text = getString(R.string.chart_description)
            }
        }
        observeChartData()
    }

    /**
     * Observing data from api to update chart
     *
     */
    @Suppress("UNCHECKED_CAST")
    private fun observeChartData() {
        mainViewModel?.liveUiData?.observe(viewLifecycleOwner, {
            (it as List<ChartViewHolderModel>).let { uiModel ->
                binding?.cityAqiChart?.apply {
                    if (data == null) {
                        data = LineData(LineDataSet(
                            getEntries(uiModel),
                            context.getString(
                                R.string.chart_label,
                                mainViewModel?.selectedCity?.city ?: ""
                            )
                        ).apply {
                            mode = LineDataSet.Mode.CUBIC_BEZIER
                        })
                        data.notifyDataChanged()
                    } else {
                        data.dataSets.firstOrNull()?.let { ds ->
                            if (ds is LineDataSet) {
                                ds.values = getEntries(uiModel)
                            }
                        }
                        data.notifyDataChanged()
                    }
                    notifyDataSetChanged()
                    invalidate()
                }
            }
        })
    }

    /**
     * Map api dat to chart entry
     *
     * @param data
     * @return
     */
    private fun getEntries(data: List<ChartViewHolderModel>): List<Entry> {
        return data.mapIndexed { x, y -> Entry(x.toFloat(), y.aqi) }
    }


    override fun onDestroyView() {
        mainViewModel?.selectedCity = null
        binding = null
        super.onDestroyView()
    }


}