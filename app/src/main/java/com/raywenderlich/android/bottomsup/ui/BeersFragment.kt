package com.raywenderlich.android.bottomsup.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager

import com.raywenderlich.android.bottomsup.R
import com.raywenderlich.android.bottomsup.common.subscribe
import com.raywenderlich.android.bottomsup.ui.feed.adapter.BeersAdapter
import com.raywenderlich.android.bottomsup.viewmodel.BeersViewModel
import com.raywenderlich.android.bottomsup.viewmodel.ConnViewModel
import kotlinx.android.synthetic.main.activity_beers.*
import kotlinx.android.synthetic.main.fragment_beers.*

/**
 * A simple [Fragment] subclass.
 */
class BeersFragment : Fragment() {

    private lateinit var viewModel: BeersViewModel
    private lateinit var connViewModel: ConnViewModel
    private val adapter = BeersAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUi(view)
        viewModel.errorData.subscribe(this, this::setErrorVisibility)
        viewModel.loadingData.subscribe(this, this::showLoading)
        viewModel.pageData.subscribe(this, adapter::clearIfNeeded)
        viewModel.beerData.subscribe(this, adapter::addItems)

        connViewModel.connectivity.observe(this, Observer {
            it?.run {
                if(it) {
                    Log.d("heeeeeeyyyyyyyy","INTERNET ON")
                    ConnAlert2.visibility = View.GONE
                    initializeUi(view)
                    viewModel.getBeers()
                } else {
                    Log.d("heeeeeeyyyyyyyy", "INTERNET OFF")
                    ConnAlert2.visibility = View.VISIBLE
                }
            }
        })
        viewModel.getBeers()

    }

    private fun initializeUi(view: View){
        beersListF.layoutManager = GridLayoutManager(view.context, 2)
        beersListF.itemAnimator = DefaultItemAnimator()
        beersListF.adapter = adapter

        pullToRefreshF.setOnRefreshListener(viewModel::onRefresh)
    }
    private fun showLoading(isLoading: Boolean){
        pullToRefreshF.isRefreshing = isLoading
    }
    private fun setErrorVisibility(shouldShow: Boolean){
        errorViewF.visibility = if (shouldShow) View.VISIBLE else View.GONE
        beersListF.visibility = if (!shouldShow) View.VISIBLE else View.GONE
    }
}
