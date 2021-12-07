package com.example.kodeheroesapp.presentation.fragments.main_fragment

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kodeheroesapp.R
import com.example.kodeheroesapp.databinding.ItemHeroesBinding
import com.example.kodeheroesapp.domain.enteties.Hero
import com.example.kodeheroesapp.presentation.utils.StringHelper.DC
import com.example.kodeheroesapp.presentation.utils.StringHelper.MARVEL

class MainFragmentViewHolder(
    private val binding: ItemHeroesBinding,
    private val itemIsClicked: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Hero) {
        loadHeroImage(item.imageUrl)
        binding.tvHeroesNickname.text = item.nickName
        binding.tvHeroesRealName.text = item.realName

        when (item.publisher) {
            MARVEL -> loadStudioIcon(
                binding.root.context.getString(R.string.marvel_icon_url)
            )
            DC -> loadStudioIcon(
                binding.root.context.getString(R.string.dc_icon_url)
            )
            else -> loadStudioIcon(
                binding.root.context.getString(R.string.unknown_studio_icon_url)
            )
        }

        itemView.setOnClickListener {
            itemIsClicked(item.id)
        }
    }

    private fun loadHeroImage(url: String) {
        Glide.with(binding.ivHeroesPhoto)
            .load(url)
            .into(binding.ivHeroesPhoto)
    }

    private fun loadStudioIcon(url: String) {
        Glide.with(binding.ivStudioIcon)
            .load(url)
            .placeholder(R.color.background_color)
            .circleCrop()
            .into(binding.ivStudioIcon)
    }
}