package com.ahmdalii.asteroidradar.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmdalii.asteroidradar.Constants
import com.ahmdalii.asteroidradar.Constants.FilterType
import com.ahmdalii.asteroidradar.Constants.FilterType.SAVED
import com.ahmdalii.asteroidradar.Constants.createLocalAsteroidList
import com.ahmdalii.asteroidradar.model.AsteroidEarth
import com.ahmdalii.asteroidradar.ui.main.repo.HomeRepo
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainViewModel(private val _repo: HomeRepo) : ViewModel() {

    private val _progressLoadingMutableLiveData = MutableLiveData<Boolean>()
    val progressLoadingMutableLiveData: LiveData<Boolean>
        get() = _progressLoadingMutableLiveData

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        run {
            throwable.printStackTrace()
            _errorMessageResponse.value = throwable.message
        }
    }

    private val _errorMessageResponse = MutableLiveData<String>()
    val errorMessageResponse: LiveData<String>
        get() = _errorMessageResponse

    private val _navigateToAsteroidEarthDetails = MutableLiveData<AsteroidEarth?>()
    val navigateToAsteroidEarthDetails
        get() = _navigateToAsteroidEarthDetails

    val asteroidImage = _repo.getAsteroidImage()

    private val _asteroidList = MutableLiveData<List<AsteroidEarth>>()
    val asteroidList: LiveData<List<AsteroidEarth>>
        get() = _asteroidList

    private var initiateDate: Pair<String, String> = initiateDate()

    fun initData() {
        requestAsteroidImage()
        requestAsteroidData()
        filterLocalData(SAVED)
    }

    private fun initiateDate(): Pair<String, String> {
        val day = Calendar.getInstance()

        val date = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
        val startDate = date.format(day.time)

        day.add(Calendar.DAY_OF_YEAR, Constants.DEFAULT_END_DATE_DAYS)

        val last = day.time
        val endDate = date.format(last)

        return Pair(startDate, endDate)
    }

    private fun requestAsteroidImage() {
        viewModelScope.launch(coroutineExceptionHandler) {
            val response = _repo.requestAsteroidImage()

            if (response.isSuccessful) {
                _repo.insertImage(response.body()!!)
            } else {
                _errorMessageResponse.value = response.errorBody().toString()
            }
        }
    }

    private fun requestAsteroidData() {
        viewModelScope.launch {
            _progressLoadingMutableLiveData.value = true
            val response = _repo.requestAsteroidData()

            if (response.isSuccessful) {
                createLocalAsteroidList(response)
                val newLocalList = createLocalAsteroidList(response)
                _repo.insertAsteroidEarth(newLocalList)
                _progressLoadingMutableLiveData.value = false
            } else {
                _errorMessageResponse.value = response.errorBody().toString()
                _progressLoadingMutableLiveData.value = false
            }
        }
    }

    fun filterLocalData(type: FilterType) {
        _repo.getAsteroidEarth(type, initiateDate.first)?.observeForever {
            _asteroidList.value = it
        }
    }

    fun onAsteroidEarthClicked(asteroidEarth: AsteroidEarth) {
        _navigateToAsteroidEarthDetails.value = asteroidEarth
    }

    fun onAsteroidEarthNavigated() {
        _navigateToAsteroidEarthDetails.value = null
    }
}
