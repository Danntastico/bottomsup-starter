package com.raywenderlich.android.bottomsup.ui

import androidx.paging.PageKeyedDataSource
import com.raywenderlich.android.bottomsup.model.Beer

class BeersDataSource: PageKeyedDataSource<String, Beer>(){
    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Beer>) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Beer>) {
        TODO("Not yet implemented")
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Beer>) {
        TODO("Not yet implemented")
    }
}