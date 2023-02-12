package com.juanpoveda.cocktails.data.repository.datasource.remote

import com.juanpoveda.cocktails.data.api.TheCocktailDBApi
import com.juanpoveda.cocktails.data.model.DrinksDTO
import retrofit2.Response
import javax.inject.Inject

class CocktailsRemoteDataSourceImpl @Inject constructor(private val theCocktailDBApi: TheCocktailDBApi): CocktailsRemoteDataSource {
    override suspend fun getCocktailsList(): Response<DrinksDTO> {
        return theCocktailDBApi.getCocktails()
    }
}
