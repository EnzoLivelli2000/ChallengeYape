package com.example.challengeyape.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challengeyape.R
import com.example.challengeyape.model.Recipe

class RecipeAdapter(
    private val recipeList: List<Recipe>,
    private val onClickListener: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RecipeViewHolder(layoutInflater.inflate(R.layout.item_recipe, parent, false))
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.render(recipeList[position], onClickListener)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

}