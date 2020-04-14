package com.raywenderlich.android.bottomsup.ui.feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.raywenderlich.android.bottomsup.R

/**
 * A simple [Fragment] subclass.
 */
class LostConnFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lost_conn, container, false)
    }

}
