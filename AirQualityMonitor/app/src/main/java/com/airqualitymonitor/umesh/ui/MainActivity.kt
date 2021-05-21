package com.airqualitymonitor.umesh.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.airqualitymonitor.umesh.R
import com.airqualitymonitor.umesh.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main activity hosting 2 fragments, Home and Chart
 *
 * @constructor Create empty Main activity
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.apply {
            fetchHomeData()
        }
        observeNavigationEvents()
    }

    /**
     * Observe user events and navigation data
     *
     */
    private fun observeNavigationEvents() {
        mainViewModel.navigationLiveData.observe(this, {
            if (it.action == R.id.viewholder_city_aqi) {
                findNavController(R.id.main_fragment_container).navigate(
                    R.id.action_homeFragment_to_chart_fragment,
                    it.extra
                )
            }
        })
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }


}