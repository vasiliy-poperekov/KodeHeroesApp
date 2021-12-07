package com.example.kodeheroesapp.presentation.fragments.main_fragment

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kodeheroesapp.R
import com.example.kodeheroesapp.databinding.FragmentMainBinding
import com.example.kodeheroesapp.presentation.utils.StringHelper.ALL
import com.example.kodeheroesapp.presentation.utils.StringHelper.DC
import com.example.kodeheroesapp.presentation.utils.StringHelper.MARVEL
import com.example.kodeheroesapp.presentation.view_models.MainFragmentViewModel
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)
    private val viewModel: MainFragmentViewModel by viewModel()
    private val mainAdapter = MainFragmentAdapter { heroId ->
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToBiographyFragment(heroId)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
        bindViewModel()
        binding.dropDownTextInputLayout.editText?.addTextChangedListener {
            if (!it.isNullOrEmpty()) {
                viewModel.filterHeroesList(it.toString())
            }
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        setMenuOptions()
        super.onViewStateRestored(savedInstanceState)
    }

    private fun setMenuOptions() {
        with(binding) {
            val adapter = ArrayAdapter(
                requireContext(), R.layout.item_menu_list, listOf(
                    MARVEL,
                    DC,
                    ALL
                )
            )

            val shapeAppearanceModel = ShapeAppearanceModel()
                .toBuilder()
                .setAllCorners(CornerFamily.ROUNDED, 50f)
                .build()

            val dropDownDrawable = autoCompleteTv.dropDownBackground

            if (dropDownDrawable is MaterialShapeDrawable) {
                val dropDownBackground: MaterialShapeDrawable = dropDownDrawable
                dropDownBackground.fillColor =
                    ContextCompat.getColorStateList(requireContext(), R.color.white)
                dropDownBackground.shapeAppearanceModel = shapeAppearanceModel
            }

            if (autoCompleteTv.text.isNotEmpty()) {
                adapter.filter.filter(null)
            }
            autoCompleteTv.setAdapter(adapter)
        }
    }

    private fun bindViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.heroesListFlow.collect { heroList ->
                        if (!heroList.isNullOrEmpty()) mainAdapter.submitList(heroList)
                    }
                }

                launch {
                    viewModel.changedHeroesListFlow.collect { heroesList ->
                        if (!heroesList.isNullOrEmpty()) {
                            mainAdapter.submitList(heroesList)
                        }
                    }
                }

                launch {
                    viewModel.networkingCheckerFlow.collect { isConnected ->
                        with(binding.connectionProblemTv) {
                            visibility = if (isConnected) View.INVISIBLE
                            else View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        with(binding.mainFragHeroesList) {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
    }
}