package com.juanpoveda.cocktails.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanpoveda.cocktails.databinding.FragmentCocktailListBinding
import com.juanpoveda.cocktails.presentation.base.BaseFragment
import com.juanpoveda.cocktails.presentation.home.adapter.CocktailAdapter
import com.juanpoveda.cocktails.presentation.model.UiCocktail
import dagger.hilt.android.AndroidEntryPoint

/**
 * This Fragment displays a list of cocktails
 */
@AndroidEntryPoint
class CocktailListFragment : BaseFragment<FragmentCocktailListBinding>() {

    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var adapter: CocktailAdapter

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCocktailListBinding {
        return FragmentCocktailListBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        observeCocktailListUiState()
    }

    private fun observeCocktailListUiState() {
        viewModel.uiState.collectWhileResumed {
            println("********** data collected: " + it)
            adapter.submitList(it)
            //TODO: Add logic to update the UI based on the response
        }
    }

    private fun setAdapter() {
        adapter = CocktailAdapter(this::onCocktailClicked).also {
            binding.cocktailsRv.adapter = it
            binding.cocktailsRv.layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    private fun onCocktailClicked(uiCocktail: UiCocktail) {
        //findNavController().navigate(R.id.action_SecondFragment_to_SplashScreenFragment)
    }

}
