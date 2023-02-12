package com.juanpoveda.cocktails.data.repository.datasource.remote

import com.juanpoveda.cocktails.data.model.CocktailsDTO
import retrofit2.Response

interface CocktailsRemoteDataSource {
    suspend fun getCocktailsList(): Response<CocktailsDTO>
}