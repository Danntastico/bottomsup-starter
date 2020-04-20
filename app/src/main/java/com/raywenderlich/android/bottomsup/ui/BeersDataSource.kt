package com.raywenderlich.android.bottomsup.ui

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PageKeyedDataSource
import com.raywenderlich.android.bottomsup.api.BreweryApiService
import com.raywenderlich.android.bottomsup.interaction.BreweryInteractor
import com.raywenderlich.android.bottomsup.model.Beer
import com.raywenderlich.android.bottomsup.model.BeerResponse
import com.raywenderlich.android.bottomsup.viewmodel.BeersViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeersDataSource: PageKeyedDataSource<String, Beer>(){

    private val api = BreweryApiService.createService()

    val errorData = MutableLiveData<Boolean>()
    val loadingData = MutableLiveData<Boolean>()

    val pageData = MutableLiveData<Int>()
    val beerData = MutableLiveData<List<Beer>>()

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Beer>) {
        api.getBeers( page = params.requestedLoadSize)
            .enqueue(object : Callback<BeerResponse> {
                override fun onFailure(call: Call<BeerResponse>?, t: Throwable?) {
                    loadingData.value = false
                    errorData.value = true
                }

                override fun onResponse(call: Call<BeerResponse>?, response: Response<BeerResponse>?) {
                    loadingData.value = false
                    errorData.value = false

                    response?.body()?.run {
                        updateData(this)
                    }
                }
            }
        )
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Beer>) {
        api.getBeers( page = params.requestedLoadSize)
            .enqueue(object : Callback<BeerResponse> {
                override fun onFailure(call: Call<BeerResponse>?, t: Throwable?) {
                    loadingData.value = false
                    errorData.value = true
                }

                override fun onResponse(call: Call<BeerResponse>?, response: Response<BeerResponse>?) {
                    loadingData.value = false
                    errorData.value = false

                    response?.body()?.run {
                        updateData(this)
                    }
                }
            }
        )
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Beer>) {
        api.getBeers( page = params.requestedLoadSize)
            .enqueue(object : Callback<BeerResponse> {
                override fun onFailure(call: Call<BeerResponse>?, t: Throwable?) {
                    loadingData.value = false
                    errorData.value = true
                }

                override fun onResponse(call: Call<BeerResponse>?, response: Response<BeerResponse>?) {
                    loadingData.value = false
                    errorData.value = false

                    response?.body()?.run {
                        updateData(this)
                    }
                }
            }
        )
    }

    private fun updateData(data: BeerResponse) {
        pageData.value = data.currentPage + 1
        beerData.value = data.beers
    }
}