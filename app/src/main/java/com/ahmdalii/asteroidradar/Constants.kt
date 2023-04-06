package com.ahmdalii.asteroidradar

import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.ui.setupWithNavController

object Constants {
    const val API_QUERY_DATE_FORMAT = "YYYY-MM-dd"
    const val DEFAULT_END_DATE_DAYS = 7
    const val EMPTY = ""

    lateinit var BASE_URL: String
    lateinit var API_KEY: String

    enum class FilterType {
        WEEK,
        TODAY,
        SAVED
    }
}

fun Fragment.setBaseActivityFragmentsToolbar(title: String, toolbar: Toolbar, textView: TextView) {
    (activity as MainActivity).apply {
        setHasOptionsMenu(true)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        toolbar.setupWithNavController(navController, appBarConfiguration)
        textView.text = title
    }
}

