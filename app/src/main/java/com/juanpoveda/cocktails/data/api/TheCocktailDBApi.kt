package com.juanpoveda.cocktails.data.api

import com.juanpoveda.cocktails.data.model.DrinksDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TheCocktailDBApi {

    @GET("search.php")
    suspend fun getCocktails(@Query("f") f: String = "a"): Response<DrinksDTO>
}
