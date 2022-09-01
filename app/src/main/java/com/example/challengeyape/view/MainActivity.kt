package com.example.challengeyape.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengeyape.adapter.RecipeAdapter
import com.example.challengeyape.databinding.ActivityMainBinding
import com.example.challengeyape.model.RecipeModel
import com.example.challengeyape.model.provider.RecipeProvider
import com.example.challengeyape.model.provider.SearchRecipe

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var recipeList: List<RecipeModel> = RecipeProvider.recipeList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        initSearchBar()
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

    private fun onItemSelected(recipe: RecipeModel){
        val intent = Intent(this, DetailActivity()::class.java)
        intent.putExtra("INTENT_DETAIL_NAME", recipe.name)
        intent.putExtra("INTENT_DETAIL_DESCRIPTION", recipe.description)
        intent.putExtra("INTENT_DETAIL_PHOTO", recipe.photo)
        intent.putExtra("INTENT_DETAIL_LAT", recipe.coordinates.lat)
        intent.putExtra("INTENT_DETAIL_LON", recipe.coordinates.lon)
        val array: ArrayList<String> = ArrayList<String>()
        array.addAll(recipe.ingredients)
        intent.putStringArrayListExtra("INTENT_INGREDIENTS",array)
        startActivity(intent)
    }

    private fun initSearchBar() {
        binding.searchBar.clearFocus()
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {

                recipeList = SearchRecipe(query).onSearchRecipe()

                binding.recyclerRecipe.adapter =
                    RecipeAdapter(recipeList) { recipe -> onItemSelected(recipe) }

                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                recipeList = SearchRecipe(query).onSearchRecipe()

                binding.recyclerRecipe.adapter = RecipeAdapter(
                    recipeList
                ) { recipe -> onItemSelected(recipe) }
                return true
            }

        })
    }
}