package com.juanpoveda.cocktails.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanpoveda.cocktails.data.model.CocktailsDTO
import com.juanpoveda.cocktails.domain.model.Cocktail
import com.juanpoveda.cocktails.domain.model.fold
import com.juanpoveda.cocktails.domain.usecase.GetCocktailListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCocktailListUseCase: GetCocktailListUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<List<Cocktail>?> = MutableStateFlow(null)
    val uiState: StateFlow<List<Cocktail>?>
        get() = _uiState

    fun getCocktailList(forceRefresh: Boolean = false) {
        getCocktailListUseCase(forceRefresh).map { result ->
            result.fold(
                onSuccess = { cocktails ->
                    _uiState.value = cocktails
                },
                onFailure = {
                    //_uiState.value = HomeUiState.Error(it.message)
                })
        }.flowOn(Dispatchers.IO).launchIn(viewModelScope)
    }

}
