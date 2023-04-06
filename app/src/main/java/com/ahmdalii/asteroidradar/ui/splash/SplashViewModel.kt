package com.ahmdalii.asteroidradar.ui.splash

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel : ViewModel() {

    private val _navigateToMain = MutableLiveData<Boolean?>()
    val navigateToMain: LiveData<Boolean?>
        get() = _navigateToMain

    init {
        Handler(Looper.getMainLooper()).postDelayed({
            _navigateToMain.value = true
        }, 5700)
    }

    override fun onCleared() {
        super.onCleared()
        _navigateToMain.value = null
    }
}
