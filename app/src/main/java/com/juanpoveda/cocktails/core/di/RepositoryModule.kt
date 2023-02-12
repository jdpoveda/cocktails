package com.juanpoveda.cocktails.core.di

import com.juanpoveda.cocktails.data.api.TheCocktailDBApi
import com.juanpoveda.cocktails.data.repository.CocktailsRepositoryImpl
import com.juanpoveda.cocktails.data.repository.datasource.remote.CocktailsRemoteDataSource
import com.juanpoveda.cocktails.data.repository.datasource.remote.CocktailsRemoteDataSourceImpl
import com.juanpoveda.cocktails.domain.repository.CocktailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideCocktailsRemoteDataSource(theCocktailDBApi: TheCocktailDBApi): CocktailsRemoteDataSource {
        return CocktailsRemoteDataSourceImpl(theCocktailDBApi)
    }

    @Provides
    fun provideCocktailsRepository(
        cocktailsRemoteDataSource: CocktailsRemoteDataSource
    ): CocktailsRepository {
        return CocktailsRepositoryImpl(
            cocktailsRemoteDataSource
        )
    }
}
