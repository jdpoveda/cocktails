package com.juanpoveda.cocktails.data.repository.datasource.local

import com.juanpoveda.cocktails.data.database.entity.CocktailEntity
import kotlinx.coroutines.flow.Flow

interface CocktailsLocalDataSource {
    fun getAllCocktails(): Flow<List<CocktailEntity>>
    suspend fun getCocktailById(cocktailId: String): CocktailEntity?
    suspend fun insert(cocktail: CocktailEntity)
    suspend fun insertAll(cocktailList: List<CocktailEntity>)
    suspend fun deleteAll()
}