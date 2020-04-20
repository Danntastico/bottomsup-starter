package com.raywenderlich.android.bottomsup.ui

import androidx.paging.PageKeyedDataSource
import com.raywenderlich.android.bottomsup.model.Beer
import com.raywenderlich.android.bottomsup.viewmodel.BeersViewModel

class BeersDataSource: PageKeyedDataSource<String, Beer>(){

    private lateinit var viewModel : BeersViewModel

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Beer>) {
        viewModel.getBeers()
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Beer>) {
        viewModel.getBeers()
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Beer>) {
        viewModel.getBeers()
    }
}