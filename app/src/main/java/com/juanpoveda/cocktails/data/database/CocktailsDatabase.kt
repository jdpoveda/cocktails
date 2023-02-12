package com.juanpoveda.cocktails.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.juanpoveda.cocktails.data.database.dao.CocktailDao
import com.juanpoveda.cocktails.data.database.entity.CocktailEntity

@Database(
    entities = [CocktailEntity::class],
    version = 1,
    exportSchema = true
)
abstract class CocktailsDatabase : RoomDatabase() {

    abstract fun CocktailDao(): CocktailDao

}
