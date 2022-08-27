package com.example.challengeyape

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.challengeyape.model.Coordinates
import com.example.challengeyape.model.Recipe

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        getDetailsRecipe()
    }

    fun getDetailsRecipe() {
        val bundle = intent.extras
        val recipe = Recipe(
            bundle?.get("INTENT_DETAIL_NAME").toString(),
            bundle?.get("INTENT_DETAIL_DESCRIPTION").toString(),
            bundle?.get("INTENT_DETAIL_PHOTO").toString(),
            Coordinates(
                bundle?.get("INTENT_DETAIL_LAT").toString(),
                bundle?.get("INTENT_DETAIL_LON").toString()
            )
        )

        if(recipe != null){
            println("tvName is not empty")
            val name = findViewById<TextView>(R.id.tvRecipeName)
            val description = findViewById<TextView>(R.id.tvRecipeDescription)
            val photo = findViewById<ImageView>(R.id.ivRecipe)
            name.text = recipe.name
            description.text = recipe.description
            Glide.with(photo.context).load(recipe.photo).into(photo)


        }
        println("tvName is empty")
        return
        //Toast.makeText(this, recipe.description, Toast.LENGTH_LONG).show()
    }
}