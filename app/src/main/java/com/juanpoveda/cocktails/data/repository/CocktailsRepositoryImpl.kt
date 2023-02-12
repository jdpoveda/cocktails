package com.juanpoveda.cocktails.data.repository

import com.juanpoveda.cocktails.data.model.DrinksDTO
import com.juanpoveda.cocktails.domain.model.Result
import com.juanpoveda.cocktails.data.repository.datasource.remote.CocktailsRemoteDataSource
import com.juanpoveda.cocktails.domain.repository.CocktailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CocktailsRepositoryImpl @Inject constructor (
    private val cocktailsRemoteDataSource: CocktailsRemoteDataSource
): CocktailsRepository {
    override fun getCocktailList(): Flow<Result<DrinksDTO>> = flow {

        val response = cocktailsRemoteDataSource.getCocktailsList()

        if (response.isSuccessful) {
            response.body()?.let {
                emit(
                    Result.Success(it)
                )
            }
        }

    }
}
