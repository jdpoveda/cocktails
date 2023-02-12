package com.juanpoveda.cocktails.data.repository.datasource.local

import com.juanpoveda.cocktails.data.database.dao.CocktailDao
import com.juanpoveda.cocktails.data.database.entity.CocktailEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CocktailsLocalDataSourceImpl @Inject constructor(
    private val cocktailDao: CocktailDao
) : CocktailsLocalDataSource {

    override fun getAllCocktails(): Flow<List<CocktailEntity>> {
        return cocktailDao.getAll()
    }

    override suspend fun getCocktailById(cocktailId: String): CocktailEntity? {
        return cocktailDao.getCocktailById(cocktailId)
    }

    override suspend fun insert(cocktail: CocktailEntity) {
        cocktailDao.insert(cocktail)
    }

    override suspend fun insertAll(cocktailList: List<CocktailEntity>) {
        cocktailDao.insertAll(cocktailList)
    }

    override suspend fun deleteAll() {
        cocktailDao.deleteAll()
    }

}
