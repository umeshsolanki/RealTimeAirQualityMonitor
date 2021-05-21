package com.airqualitymonitor.umesh.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.airqualitymonitor.umesh.R
import com.airqualitymonitor.umesh.databinding.FragmentHomeBinding
import com.airqualitymonitor.umesh.ui.MainActivity
import com.airqualitymonitor.umesh.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Home fragment
 *
 * @constructor Create empty Home fragment
 */
@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding? = null
    private var mainViewModel: MainViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        if (mainViewModel == null) {
            mainViewModel = (activity as? MainActivity)?.mainViewModel
        }
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = mainViewModel
        }
    }


    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }


}