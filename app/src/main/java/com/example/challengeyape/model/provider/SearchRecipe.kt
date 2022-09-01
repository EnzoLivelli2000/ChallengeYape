package com.example.challengeyape.model.provider

import com.example.challengeyape.model.RecipeModel

class SearchRecipe(private val query: String) {
    lateinit var recipeList: List<RecipeModel>

    fun onSearchRecipe(): List<RecipeModel> {
        recipeList =
            RecipeProvider.recipeList.filter { recipe: RecipeModel ->
                recipe.name.contains(
                    query,
                    ignoreCase = true
                )
            }
        return if (recipeList.isNotEmpty()) {
            recipeList
        } else {
            return listOf()
        }
    }

}