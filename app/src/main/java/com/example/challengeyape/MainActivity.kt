package com.example.challengeyape

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SearchEvent
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengeyape.Adapter.RecipeAdapter
import com.example.challengeyape.databinding.ActivityMainBinding
import com.example.challengeyape.model.Coordinates
import com.example.challengeyape.model.Recipe
import com.example.challengeyape.provider.RecipeProvider

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var recipeList: List<Recipe> = RecipeProvider.recipeList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        initSearchBar()
    }

    override fun onSearchRequested(searchEvent: SearchEvent?): Boolean {
        println("onContentChanged")
        return super.onSearchRequested(searchEvent)
    }

    private fun initRecyclerView() {
        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, manager.orientation)
        binding.recyclerRecipe.layoutManager = manager
        binding.recyclerRecipe.adapter = RecipeAdapter(
            RecipeProvider.recipeList
        ) { recipe -> onItemSelected(recipe) }

        binding.recyclerRecipe.addItemDecoration(decoration)
    }

    fun onItemSelected(recipe: Recipe) {
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("INTENT_DETAIL_NAME", recipe.name)
        intent.putExtra("INTENT_DETAIL_DESCRIPTION", recipe.description)
        intent.putExtra("INTENT_DETAIL_PHOTO", recipe.photo)
        intent.putExtra("INTENT_DETAIL_LAT", recipe.coordinates?.lat)
        intent.putExtra("INTENT_DETAIL_LON", recipe.coordinates?.lon)

        startActivity(intent)
    }

    private fun initSearchBar() {
        println("Search bar response")
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                println("query -> $query")
                binding.searchBar.clearFocus()
                recipeList =
                    RecipeProvider.recipeList.filter { recipe: Recipe -> recipe.name == query }
                println("recipe -> $recipeList")
                if (recipeList.isNotEmpty()) {
                    println("search bar response Ceviche-> $recipeList")
                    binding.recyclerRecipe.adapter = RecipeAdapter(
                        recipeList
                    ) { recipe -> onItemSelected(recipe) }
                    return true
                }
                println("Return False -> onQueryTextSubmit")
                binding.recyclerRecipe.adapter = RecipeAdapter(
                    listOf()
                ) { recipe -> onItemSelected(recipe) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                recipeList =
                    RecipeProvider.recipeList.filter { recipe: Recipe -> recipe.name == newText }
                println("Return False -> onQueryTextChange")
                if (recipeList.isNotEmpty()) {
                    println("search bar response Ceviche-> $recipeList")
                    binding.recyclerRecipe.adapter = RecipeAdapter(
                        recipeList
                    ) { recipe -> onItemSelected(recipe) }
                    return true
                }
                binding.recyclerRecipe.adapter = RecipeAdapter(
                    RecipeProvider.recipeList
                ) { recipe -> onItemSelected(recipe) }
                return false
            }

        })
    }
}