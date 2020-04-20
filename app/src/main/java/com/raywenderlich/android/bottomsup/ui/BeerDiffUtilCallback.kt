package com.raywenderlich.android.bottomsup.ui

import androidx.recyclerview.widget.DiffUtil
import com.raywenderlich.android.bottomsup.model.Beer

class BeerDiffUtilCallback : DiffUtil.ItemCallback<Beer>(){
    override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean {
        return oldItem.name == newItem.name
                && oldItem.labels == newItem.labels
                && oldItem.style == newItem.style
    }
}