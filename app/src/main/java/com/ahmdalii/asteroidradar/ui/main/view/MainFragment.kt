package com.ahmdalii.asteroidradar.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ahmdalii.asteroidradar.Constants.FilterType.SAVED
import com.ahmdalii.asteroidradar.Constants.FilterType.TODAY
import com.ahmdalii.asteroidradar.Constants.FilterType.WEEK
import com.ahmdalii.asteroidradar.R
import com.ahmdalii.asteroidradar.api.AsteroidClient
import com.ahmdalii.asteroidradar.database.LocalSourceImpl
import com.ahmdalii.asteroidradar.databinding.FragmentMainBinding
import com.ahmdalii.asteroidradar.setBaseActivityFragmentsToolbar
import com.ahmdalii.asteroidradar.ui.main.repo.HomeRepoImpl
import com.ahmdalii.asteroidradar.ui.main.viewmodel.MainViewModel
import com.ahmdalii.asteroidradar.ui.main.viewmodel.MainViewModelFactory

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    private lateinit var binding: FragmentMainBinding

    private lateinit var adapter: AsteroidEarthAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)

        initiateViewModel()
        initiateAdapter()

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.homeToolbar.apply { setBaseActivityFragmentsToolbar(getString(R.string.app_name), toolbar, tvNameToolbar) }

        /*setHasOptionsMenu(true)*/
        setMenu()

        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.initData()
        }

        return binding.root
    }

    private fun initiateViewModel() {
        val viewModelFactory = MainViewModelFactory(
            HomeRepoImpl.getInstance(
                AsteroidClient.getInstance(),
                LocalSourceImpl(requireContext())
            )
        )
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        viewModel.errorMessageResponse.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
        }

        viewModel.asteroidList.observe(viewLifecycleOwner) { asteroidEarthList ->
            adapter.submitList(asteroidEarthList)
        }

        viewModel.progressLoadingMutableLiveData.observe(viewLifecycleOwner) { loading ->
            binding.apply {
                swipeToRefresh.isRefreshing = false
                if (loading == null || loading) {
                    shimmer.visibility = View.VISIBLE
                    shimmer.startShimmer()
                    swipeToRefresh.visibility = View.GONE
                } else {
                    shimmer.visibility = View.GONE
                    shimmer.stopShimmer()
                    swipeToRefresh.visibility = View.VISIBLE
                }
            }
        }

        viewModel.navigateToAsteroidEarthDetails.observe(viewLifecycleOwner) { asteroidEarth ->
            asteroidEarth?.let {
                this.findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToDetailFragment(asteroidEarth)
                )
                viewModel.onAsteroidEarthNavigated()
            }
        }
    }

    private fun initiateAdapter() {
        adapter = AsteroidEarthAdapter(
            AsteroidEarthListener { asteroidEarth ->
                viewModel.onAsteroidEarthClicked(asteroidEarth)
            }
        )
        binding.asteroidRecycler.adapter = adapter
    }

    private fun setMenu() {
        (requireActivity() as MenuHost).addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.main_overflow_menu, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    when (menuItem.itemId) {
                        R.id.show_week_menu -> {
                            viewModel.filterLocalData(WEEK)
                        }
                        R.id.show_today_menu -> {
                            viewModel.filterLocalData(TODAY)
                        }
                        R.id.show_saved_menu -> {
                            viewModel.filterLocalData(SAVED)
                        }
                    }
                    return true
                }
            },
            viewLifecycleOwner
        )
    }

    /*override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }*/
}
