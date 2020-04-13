/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.bottomsup.ui.feed

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.wifi.p2p.WifiP2pManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.raywenderlich.android.bottomsup.R
import com.raywenderlich.android.bottomsup.common.getViewModel
import com.raywenderlich.android.bottomsup.common.subscribe
import com.raywenderlich.android.bottomsup.ui.feed.adapter.BeersAdapter
import com.raywenderlich.android.bottomsup.viewmodel.BeersViewModel
import kotlinx.android.synthetic.main.activity_beers.*
import kotlinx.android.synthetic.main.fragment_connection_alert.*


class BeersActivity : AppCompatActivity() {


  private val viewModel by lazy { getViewModel<BeersViewModel>()}
  private val adapter = BeersAdapter()


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_beers)
    setActionBar(findViewById(R.id.toolBarMain))
    initializeUi()
    viewModel.errorData.subscribe(this, this::setErrorVisibility)
    viewModel.loadingData.subscribe(this, this::showLoading)
    viewModel.pageData.subscribe(this, adapter::clearIfNeeded)
    viewModel.beerData.subscribe(this, adapter::addItems)
    viewModel.getBeers()
  }
  private fun initializeUi(){
    beersList.layoutManager = GridLayoutManager(this, 2)
    beersList.itemAnimator = DefaultItemAnimator()
    beersList.adapter = adapter

    pullToRefresh.setOnRefreshListener ( viewModel::onRefresh )
  }
  private fun showLoading(isLoading: Boolean){
    pullToRefresh.isRefreshing = isLoading
  }
  private fun setErrorVisibility(shouldShow: Boolean){
    errorView.visibility = if (shouldShow) View.VISIBLE else View.GONE
    beersList.visibility = if (!shouldShow) View.VISIBLE else View.GONE
  }

  private fun isInternetAvailable(): Boolean {
    var result = false
    val connectivityManager =
            baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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
  //acá puede ir mi método para la carga infinita!
}
