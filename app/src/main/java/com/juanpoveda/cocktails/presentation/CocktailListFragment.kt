package com.juanpoveda.cocktails.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.juanpoveda.cocktails.databinding.FragmentCocktailListBinding
import com.juanpoveda.cocktails.presentation.base.BaseFragment
import com.juanpoveda.cocktails.presentation.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * This Fragment displays a list of cocktails
 */
@AndroidEntryPoint
class CocktailListFragment : BaseFragment<FragmentCocktailListBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //findNavController().navigate(R.id.action_SecondFragment_to_SplashScreenFragment)
        viewModel.getCocktailList()
        viewModel.uiState.collectWhileResumed {
            println("********** data collected: " + it)
            //TODO: Add logic to update the UI based on the response
        }
    }

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCocktailListBinding {
        return FragmentCocktailListBinding.inflate(inflater, container, false)
    }

}
