package com.raywenderlich.android.bottomsup.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.raywenderlich.android.bottomsup.R
import kotlinx.android.synthetic.main.item_beer.*
import kotlinx.android.synthetic.main.item_beer.view.*

class ItemsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_beer, container, false)
    }
}