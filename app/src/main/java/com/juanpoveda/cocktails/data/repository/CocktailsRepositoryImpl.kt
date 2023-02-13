package com.juanpoveda.cocktails.data.repository

import com.juanpoveda.cocktails.data.mapper.CocktailResponseMapper
import com.juanpoveda.cocktails.data.model.CocktailsDTO
import com.juanpoveda.cocktails.data.repository.datasource.local.CocktailsLocalDataSource
import com.juanpoveda.cocktails.domain.model.Result
import com.juanpoveda.cocktails.data.repository.datasource.remote.CocktailsRemoteDataSource
import com.juanpoveda.cocktails.data.utils.NetworkUtils
import com.juanpoveda.cocktails.domain.model.Cocktail
import com.juanpoveda.cocktails.domain.model.DomainException
import com.juanpoveda.cocktails.domain.repository.CocktailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CocktailsRepositoryImpl @Inject constructor (
    private val cocktailsRemoteDataSource: CocktailsRemoteDataSource,
    private val cocktailsLocalDataSource: CocktailsLocalDataSource,
    private val cocktailResponseMapper: CocktailResponseMapper,
    private val networkUtils: NetworkUtils
): CocktailsRepository {

    override fun getCocktailList(forceRefresh: Boolean): Flow<Result<List<Cocktail>>> = flow {
        try {
            // Get cocktails from the local database
            val lastUpdate: Long = cocktailsLocalDataSource.getAllCocktails().first().lastOrNull()?.createdAt ?: 0L
            val cacheHasExpired = cacheHasExpired(lastUpdate)

            if (networkUtils.isNetworkAvailable() && cacheHasExpired || forceRefresh) {

                val response = cocktailsRemoteDataSource.getCocktailsList()

                if (response.isSuccessful) {
                    response.body()?.let {
                        cocktailsLocalDataSource.deleteAll()
                        cocktailsLocalDataSource.insertAll(
                            it.drinks.map {drinksList ->
                                cocktailResponseMapper.mapResponseToEntity(drinksList)
                            }
                        )
                    }
                }
            }

            emit(
                Result.Success(
                    cocktailsLocalDataSource.getAllCocktails().first()
                        .map { cocktailResponseMapper.mapEntityToDomain(it) })
            )

        } catch (e: Exception) {
            emit(Result.Error(DomainException(e.message ?: "Something went wrong")))
        }
    }

    private fun cacheHasExpired(lastUpdate: Long): Boolean {
        val currentTime = System.currentTimeMillis()
        val cacheTime = currentTime - lastUpdate

        return cacheTime > DEFAULT_REFRESH_RATE_MS
    }

    companion object {
        private val DEFAULT_REFRESH_RATE_MS = TimeUnit.DAYS.toMillis(1)
    }

}
