package com.juanpoveda.cocktails.domain.mapper

import com.juanpoveda.cocktails.domain.model.Cocktail
import com.juanpoveda.cocktails.presentation.model.UiCocktail
import com.juanpoveda.cocktails.presentation.model.UiIngredient

class CocktailMapper {

    fun mapToUiCocktail(cocktail: Cocktail) = UiCocktail(
        id = cocktail.id,
        name = cocktail.name,
        alternate = cocktail.alternate,
        tags = cocktail.tags,
        video = cocktail.video,
        category = cocktail.category,
        IBA = cocktail.IBA,
        type = cocktail.type,
        glass = cocktail.glass,
        instructions = cocktail.instructions,
        instructionsES = cocktail.instructionsES,
        thumb = cocktail.thumb,
        dateModified = cocktail.dateModified,
        imageSource = cocktail.imageSource,
        ingredients = cocktail.ingredients.filterKeys { it != null }.map {entry ->
            UiIngredient(entry.key, entry.value)
        }
    )
    
}
