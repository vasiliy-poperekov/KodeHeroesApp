package com.example.kodeheroesapp.presentation.fragments.main_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.kodeheroesapp.databinding.ItemHeroesBinding
import com.example.kodeheroesapp.domain.enteties.Hero

class MainFragmentAdapter(
    private val itemIsClicked: (Int) -> Unit
) :
    ListAdapter<Hero, MainFragmentViewHolder>(MainFragmentDiffUtilItemCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFragmentViewHolder {
        return MainFragmentViewHolder(
            ItemHeroesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ) { itemIsClicked(it) }
    }

    override fun onBindViewHolder(holder: MainFragmentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}