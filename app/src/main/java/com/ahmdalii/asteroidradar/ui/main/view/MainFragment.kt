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
import com.ahmdalii.asteroidradar.ConnectionLiveData
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
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    private lateinit var myView: View
    private lateinit var viewModel: MainViewModel

    private lateinit var binding: FragmentMainBinding

    private lateinit var adapter: AsteroidEarthAdapter

    private var isConnectedToNetwork: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)

        initiateViewModel()
        initiateAdapter()
        listenerOnNetwork()

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.homeToolbar.apply { setBaseActivityFragmentsToolbar(getString(R.string.app_name), toolbar, tvNameToolbar) }

        setMenu()

        binding.swipeToRefresh.setOnRefreshListener {
            if (isConnectedToNetwork) {
                viewModel.initData()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myView = view
    }

    private fun listenerOnNetwork() {
        ConnectionLiveData(requireContext()).observe(viewLifecycleOwner) { isOnline ->
            isConnectedToNetwork = isOnline
            if (isOnline) {
                showShimmer()
                viewModel.initData()
            } else {
                hideShimmer()
                Snackbar.make(myView, getString(R.string.connection_lost), Snackbar.LENGTH_LONG).show()
            }
        }
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
            hideShimmer()
            adapter.submitList(asteroidEarthList)
        }

        viewModel.progressLoadingMutableLiveData.observe(viewLifecycleOwner) { loading ->
            binding.apply {
                swipeToRefresh.isRefreshing = false
                if (loading == null || loading) {
                    showShimmer()
                } else {
                    hideShimmer()
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

    private fun hideShimmer() {
        binding.shimmer.visibility = View.GONE
        binding.shimmer.stopShimmer()
        binding.swipeToRefresh.visibility = View.VISIBLE
    }

    private fun showShimmer() {
        binding.shimmer.visibility = View.VISIBLE
        binding.shimmer.startShimmer()
        binding.swipeToRefresh.visibility = View.GONE
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
}
