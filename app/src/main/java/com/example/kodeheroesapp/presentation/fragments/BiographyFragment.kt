package com.example.kodeheroesapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.kodeheroesapp.R
import com.example.kodeheroesapp.databinding.FragmentBiographyBinding
import com.example.kodeheroesapp.presentation.utils.StringHelper.DC
import com.example.kodeheroesapp.presentation.utils.StringHelper.MARVEL
import com.example.kodeheroesapp.presentation.view_models.BiographyFragmentViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class BiographyFragment : Fragment(R.layout.fragment_biography) {
    private val binding: FragmentBiographyBinding by viewBinding(FragmentBiographyBinding::bind)
    private val viewModel: BiographyFragmentViewModel by viewModel()

    private val args: BiographyFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.getHeroBiography(args.heroId)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindViewModel()
    }

    private fun bindViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.heroBiographyFlow.collect { heroBiography ->
                        if (heroBiography != null) {
                            with(binding) {
                                Glide.with(ivHeroesPhoto)
                                    .load(heroBiography.hero.imageUrl)
                                    .into(ivHeroesPhoto)

                                tvHeroesNickname.text = heroBiography.hero.nickName
                                tvHeroesRealName.text =
                                    tvHeroesRealName.text.toString() + heroBiography.hero.realName
                                tvAliases.text =
                                    tvAliases.text.toString() + heroBiography.aliases.joinToString(", ")
                                tvBirthPlace.text =
                                    tvBirthPlace.text.toString() + heroBiography.placeOfBirth
                                tvRelatives.text =
                                    tvRelatives.text.toString() + heroBiography.relatives

                                when (heroBiography.hero.publisher) {
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
                            }
                        }
                    }
                }

                launch {
                    viewModel.connectionFlow.collect { isConnected ->
                        with(binding.connectionProblemTv) {
                            visibility = if (isConnected) View.INVISIBLE
                            else View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    private fun loadStudioIcon(url: String) {
        Glide.with(binding.ivStudioIcon)
            .load(url)
            .placeholder(R.color.background_color)
            .circleCrop()
            .into(binding.ivStudioIcon)
    }
}