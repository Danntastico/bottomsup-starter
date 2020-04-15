package com.raywenderlich.android.bottomsup.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.raywenderlich.android.bottomsup.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBar(findViewById(R.id.toolBarMain))
        configNav()
    }

    fun configNav(){
        NavigationUI.setupWithNavController(
                bnMenu,
                Navigation.findNavController(this, R.id.fragContent))
    }
}
