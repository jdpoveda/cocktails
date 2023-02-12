package com.juanpoveda.cocktails.domain.repository

import com.juanpoveda.cocktails.domain.model.Cocktail
import com.juanpoveda.cocktails.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface CocktailsRepository {

    fun getCocktailList(forceRefresh: Boolean = false): Flow<Result<List<Cocktail>>>

}