package com.juanpoveda.cocktails.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cocktail")
data class CocktailEntity(
    @PrimaryKey
    val id: String,
    val name: String?,
    val alternate: String?,
    val tags: String?,
    val video: String?,
    val category: String?,
    val IBA: String?,
    val type: String?,
    val glass: String?,
    val instructions: String?,
    val instructionsES: String?,
    val drinkThumb: String?,
    val ingredients: HashMap<String?, String?>,
    val imageSource: String?,
    val dateModified: String?
)
