package com.raywenderlich.android.bottomsup.network

import android.Manifest
import android.app.Application
import android.content.Context
import android.net.*
import android.os.Build
import androidx.annotation.RequiresPermission
import androidx.lifecycle.LiveData


class ConnectivityOnlineLiveData internal constructor(private val connectivityManager: ConnectivityManager) : LiveData<Boolean>() {

    private var lastValue: Boolean? = null

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_NETWORK_STATE])
    constructor(application: Application) : this(
            application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    )

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network?) {
            oneTimeNotify()
        }

        override fun onLost(network: Network?) {
            oneTimeNotify()
        }

    }

    override fun onActive() {
        super.onActive()
        oneTimeNotify()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        } else {
            val builder = NetworkRequest.Builder()
                    .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                    .addTransportType(NetworkCapabilities.TRANSPORT_BLUETOOTH)
                    .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
            connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
        }
    }

    private fun oneTimeNotify() {
        val newValue = isInternetOn()

        if (lastValue != newValue) {
            postValue(newValue)
            lastValue = newValue
        }
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    private fun isNetworkIsAvailable(): Boolean {
        var result = false

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                    connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }

        return result
    }

    @RequiresPermission(android.Manifest.permission.INTERNET)
    private fun isInternetOn(): Boolean {
        if (isNetworkIsAvailable()) {
            try {
                val p = Runtime.getRuntime().exec("ping -c 1 www.google.com")
                val value = p.waitFor()
                return value == 0

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return false
    }
}