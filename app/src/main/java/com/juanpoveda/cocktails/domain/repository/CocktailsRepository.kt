package com.juanpoveda.cocktails.domain.repository

import com.juanpoveda.cocktails.data.model.DrinksDTO
import com.juanpoveda.cocktails.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface CocktailsRepository {
    fun getCocktailList(): Flow<Result<DrinksDTO>>
}