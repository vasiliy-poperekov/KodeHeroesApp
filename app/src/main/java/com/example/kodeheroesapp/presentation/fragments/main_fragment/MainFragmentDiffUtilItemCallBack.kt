package com.example.kodeheroesapp.presentation.fragments.main_fragment

import androidx.recyclerview.widget.DiffUtil
import com.example.kodeheroesapp.domain.enteties.Hero

class MainFragmentDiffUtilItemCallBack: DiffUtil.ItemCallback<Hero>() {
    override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean {
        return oldItem.id == newItem.id
    }
}