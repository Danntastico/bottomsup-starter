package com.raywenderlich.android.bottomsup.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.raywenderlich.android.bottomsup.network.ConnectivityOnlineLiveData

class ConnViewModel(app: Application) : AndroidViewModel(app) {
    val connectivity: LiveData<Boolean>
    init {
        connectivity = ConnectivityOnlineLiveData(app)
    }
}