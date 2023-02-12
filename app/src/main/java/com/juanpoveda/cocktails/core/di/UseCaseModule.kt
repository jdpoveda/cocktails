package com.juanpoveda.cocktails.core.di

import com.juanpoveda.cocktails.domain.repository.CocktailsRepository
import com.juanpoveda.cocktails.domain.usecase.GetCocktailListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetCocktailListUseCase(cocktailsRepository: CocktailsRepository) =
        GetCocktailListUseCase(cocktailsRepository)

}
