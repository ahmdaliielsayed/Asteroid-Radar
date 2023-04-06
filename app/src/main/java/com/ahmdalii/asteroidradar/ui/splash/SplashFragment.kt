package com.ahmdalii.asteroidradar.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ahmdalii.asteroidradar.R
import com.ahmdalii.asteroidradar.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentSplashBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)

        initiateViewModel()

        return binding.root
    }

    private fun initiateViewModel() {
        val splashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]

        splashViewModel.navigateToMain.observe(viewLifecycleOwner) { navigateToMain ->
            if (navigateToMain == true) {
                navigateToMain()
            }
        }
    }

    private fun navigateToMain() {
        this.findNavController().navigate(
            SplashFragmentDirections.actionSplashFragmentToMainFragment()
        )
    }
}
