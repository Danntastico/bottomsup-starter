package com.raywenderlich.android.bottomsup.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.raywenderlich.android.bottomsup.R
import com.raywenderlich.android.bottomsup.common.getViewModel
import com.raywenderlich.android.bottomsup.ui.feed.adapter.BeersAdapter
import com.raywenderlich.android.bottomsup.viewmodel.BeersViewModel
import com.raywenderlich.android.bottomsup.viewmodel.ConnViewModel

/**
 * A simple [Fragment] subclass.
 */
class BeersFragment : Fragment() {

    private val viewModel by lazy { getViewModel<BeersViewModel>()}
    private val connViewModel by lazy {getViewModel<ConnViewModel>()}
    private val adapter = BeersAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beers, container, false)
    }

}
