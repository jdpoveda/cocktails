package com.juanpoveda.cocktails.domain.usecase

import com.juanpoveda.cocktails.data.model.DrinksDTO
import com.juanpoveda.cocktails.domain.model.Result
import com.juanpoveda.cocktails.domain.model.fold
import com.juanpoveda.cocktails.domain.repository.CocktailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCocktailListUseCase @Inject constructor(
    private val cocktailsRepository: CocktailsRepository
) {
    operator fun invoke(forceRefresh: Boolean): Flow<Result<DrinksDTO>> = flow {
        cocktailsRepository.getCocktailList().map { result ->
            result.fold(
                onSuccess = { cocktailsList ->
                    emit(Result.Success(cocktailsList))
                },
                onFailure = {
                    emit(Result.Error(it))
                }
            )
        }.collect()
    }
}
