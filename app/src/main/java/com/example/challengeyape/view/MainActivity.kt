package com.example.challengeyape.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengeyape.adapter.RecipeAdapter
import com.example.challengeyape.databinding.ActivityMainBinding
import com.example.challengeyape.model.RecipeModel
import com.example.challengeyape.model.provider.SearchRecipe
import com.example.challengeyape.services.IApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var recipeList = mutableListOf<RecipeModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getAllRecipes()
        initRecyclerView()
        initSearchBar()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://demo3732690.mockable.io/yapechallenge/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getAllRecipes() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(IApiService::class.java).getRecipes()
            val recipes = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    if (recipes != null) {
                        recipeList.clear()
                        recipeList.addAll(recipes)
                        binding.recyclerRecipe.adapter?.notifyDataSetChanged()

                    } else {
                        recipeList.addAll(emptyList())
                    }
                } else {
                    showError()
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    private fun initRecyclerView() {

        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, manager.orientation)
        binding.recyclerRecipe.layoutManager = manager
        binding.recyclerRecipe.adapter = RecipeAdapter(
            recipeList
        ) { recipe -> onItemSelected(recipe) }

        binding.recyclerRecipe.addItemDecoration(decoration)
    }

    private fun onItemSelected(recipe: RecipeModel) {
        val intent = Intent(this, DetailActivity()::class.java)
        intent.putExtra("INTENT_DETAIL_NAME", recipe.name)
        intent.putExtra("INTENT_DETAIL_DESCRIPTION", recipe.description)
        intent.putExtra("INTENT_DETAIL_PHOTO", recipe.photo)
        intent.putExtra("INTENT_DETAIL_LAT", recipe.coordinates.lat)
        intent.putExtra("INTENT_DETAIL_LON", recipe.coordinates.lon)
        val array: ArrayList<String> = ArrayList<String>()
        array.addAll(recipe.ingredients)
        intent.putStringArrayListExtra("INTENT_INGREDIENTS", array)
        startActivity(intent)
    }

    private fun initSearchBar() {
        binding.searchBar.clearFocus()
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {

                recipeList = SearchRecipe(query).onSearchRecipe() as MutableList<RecipeModel>

                binding.recyclerRecipe.adapter =
                    RecipeAdapter(recipeList) { recipe -> onItemSelected(recipe) }

                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                recipeList = SearchRecipe(query).onSearchRecipe() as MutableList<RecipeModel>

                binding.recyclerRecipe.adapter = RecipeAdapter(
                    recipeList
                ) { recipe -> onItemSelected(recipe) }
                return true
            }

        })
    }
}