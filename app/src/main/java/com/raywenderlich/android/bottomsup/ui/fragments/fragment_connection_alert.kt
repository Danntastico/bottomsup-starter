package com.raywenderlich.android.bottomsup.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.raywenderlich.android.bottomsup.R
/**
 * A simple [Fragment] subclass.
 */
class fragment_connection_alert : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_connection_alert, container, false)
    }

}
