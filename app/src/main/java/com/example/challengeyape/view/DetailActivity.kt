package com.example.challengeyape.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.challengeyape.databinding.ActivityDetailBinding
import com.example.challengeyape.model.CoordinatesModel
import com.example.challengeyape.model.RecipeModel

class DetailActivity() : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDetailsRecipe()

        binding.btnMap.setOnClickListener { onClickMapButton() }
        binding.btnIngredients.setOnClickListener { onCLickIngredientsButton() }
    }

    fun getDetailsRecipe() {
        val bundle = intent.extras
        val recipe = RecipeModel(
            bundle?.get("INTENT_DETAIL_NAME").toString(),
            bundle?.get("INTENT_DETAIL_DESCRIPTION").toString(),
            bundle?.get("INTENT_DETAIL_PHOTO").toString(),
            CoordinatesModel(
                bundle?.get("INTENT_DETAIL_LAT").toString(),
                bundle?.get("INTENT_DETAIL_LON").toString()
            ),
            bundle?.getStringArrayList("INTENT_INGREDIENTS")!!
        )

        val photo = binding.ivRecipe

        binding.tvRecipeName.text = recipe.name
        binding.tvRecipeDescription.text = recipe.description
        Glide.with(photo.context).load(recipe.photo).into(photo)
    }

    private fun onClickMapButton() {
        val bundle = intent.extras
        val coordinates = CoordinatesModel(
            bundle?.get("INTENT_DETAIL_LAT").toString(),
            bundle?.get("INTENT_DETAIL_LON").toString()
        )

        val intent = Intent(this, MapActivity::class.java)

        intent.putExtra("INTENT_DETAIL_LAT", coordinates.lat)
        intent.putExtra("INTENT_DETAIL_LON", coordinates.lon)

        startActivity(intent)
    }

    private fun onCLickIngredientsButton(){
        val bundle = intent.extras
        val ingredients = bundle?.getStringArrayList("INTENT_INGREDIENTS")

        val intent = Intent(this, IngredientsActivity::class.java)

        intent.putStringArrayListExtra("INTENT_INGREDIENTS",ingredients)

        startActivity(intent)
    }
}