package com.ahmdalii.asteroidradar.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ahmdalii.asteroidradar.ui.main.repo.HomeRepo

class MainViewModelFactory(private val _repo: HomeRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(_repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
