package com.example.challengeyape.services

import com.example.challengeyape.model.RecipeModel
import retrofit2.Response
import retrofit2.http.GET

interface IApiService {
    @GET("recipes")
    suspend fun getRecipes():Response<List<RecipeModel>>
}